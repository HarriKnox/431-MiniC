package llvm;


import java.io.PrintWriter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import llvm.instruction.LLVMInstruction;

import llvm.link.LLVMBranch;
import llvm.link.LLVMJump;
import llvm.link.LLVMLink;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;


public class LLVMCFGNode
{
   public final List<LLVMInstruction> instructions = new LinkedList<>();
   public final List<LLVMCFGNode> predecessors = new LinkedList<>();
   public LLVMCFGNode loopback = null;
   public LLVMLink link = null;
   public final boolean unreachable;
   
   
   private ARMCFGNode armNode = null;
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMCFGNode(boolean unreachable)
   {
      this.unreachable = unreachable;
   }
   
   
   public LLVMCFGNode add(LLVMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
   
   
   public void jump(LLVMCFGNode target)
   {
      target.predecessors.add(this);
      
      this.link = new LLVMJump(target, false);
   }
   
   
   public void loopback(LLVMCFGNode target)
   {
      target.loopback = this;
      
      this.link = new LLVMJump(target, true);
   }
   
   
   public void branch(LLVMOperand guard,
         LLVMCFGNode thenNode, LLVMCFGNode elseNode)
   {
      thenNode.predecessors.add(this);
      elseNode.predecessors.add(this);
      
      this.link = new LLVMBranch(guard, false, thenNode, elseNode);
   }
   
   
   public void recursivisit(List<LLVMCFGNode> nodes)
   {
      if (nodes.contains(this))
         return;
      
      
      for (LLVMCFGNode parent : this.predecessors)
         parent.recursivisit(nodes);
      
      
      if (!nodes.contains(this))
      {
         if (!nodes.isEmpty() || this.loopback != null)
            this.setUID();
         
         nodes.add(this);
      }
      
      
      if (this.loopback != null)
         this.loopback.recursivisit(nodes);
   }
   
   
   public static LLVMCFGNode cleanCFG(LLVMCFGNode exit)
   {
      removeUnreachables(exit);
      removeEmpties(exit);
      removeRedundants(exit);
      
      
      if (exit.predecessors.size() == 1)
      {
         LLVMCFGNode pred = exit.predecessors.get(0);
         
         if (pred.link instanceof LLVMJump)
         {
            pred.instructions.addAll(exit.instructions);
            pred.link = exit.link;
            exit = pred;
         }
      }
      
      
      return exit;
   }
   
   
   private static void removeUnreachables(LLVMCFGNode exit)
   {
      Queue<LLVMCFGNode> nodes = new LinkedList<>();
      Set<LLVMCFGNode> visited = new HashSet<>();
      
      nodes.add(exit);
      
      while (!nodes.isEmpty())
      {
         LLVMCFGNode node = nodes.remove();
         
         
         if (visited.contains(node))
            continue;
         
         visited.add(node);
         
         
         if (node.unreachable)
         {
            /*
             * A branch or ret node will never be unreachable:
             * - if a branch node was unreachable then that would mean it came
             *   from an `if` or a `while` in a block that followed a
             *   return-equivalent statement, which isn't allowed
             * - the only ret node allowed is the exit node, and it is always
             *   instantiated as reachable
             */
            ((LLVMJump)node.link).target.replacePredecessor(node, null);
            continue;
         }
         
         
         for (LLVMCFGNode pred : node.predecessors)
            nodes.add(pred);
         
         if (node.loopback != null)
            nodes.add(node.loopback);
      }
   }
   
   
   private static void removeEmpties(LLVMCFGNode exit)
   {
      Queue<LLVMCFGNode> nodes = new LinkedList<>();
      Set<LLVMCFGNode> visited = new HashSet<>();
      
      nodes.add(exit);
      
      while (!nodes.isEmpty())
      {
         LLVMCFGNode node = nodes.remove();
         
         
         if (visited.contains(node))
         {
            /* Revisit a loopback node that may be able to be removed now */
            if ((node.link instanceof LLVMJump) && ((LLVMJump)node.link).loop)
            {
               LLVMCFGNode target = ((LLVMJump)node.link).target;
               
               visited.add(target);
               
               
               if (node.instructions.isEmpty()
                     && (node.predecessors.size() == 1))
                  node.predecessors.get(0).replaceLink(node, target);
            }
            
            continue;
         }
         
         
         /*
          * Don't add loopback target nodes (guard nodes for while loops) so we
          * can traverse back into them if changes to the body of the loop
          * allow the last node to be removed.
          */
         if (node.loopback == null)
            visited.add(node);
         
         
         if ((node.instructions.isEmpty())
               && (node.link instanceof LLVMJump)
               && !((LLVMJump)node.link).loop)
         {
            if (node.predecessors.isEmpty())
               ((LLVMJump)node.link).target.replacePredecessor(node, null);
            
            else
               for (LLVMCFGNode pred : node.predecessors)
                  pred.replaceLink(node, ((LLVMJump)node.link).target);
         }
         
         
         for (LLVMCFGNode pred : node.predecessors)
            nodes.add(pred);
         
         if (node.loopback != null)
            nodes.add(node.loopback);
      }
   }
   
   
   private static void removeRedundants(LLVMCFGNode exit)
   {
      Queue<LLVMCFGNode> nodes = new LinkedList<>();
      Set<LLVMCFGNode> visited = new HashSet<>();
      
      
      for (LLVMCFGNode pred : exit.predecessors)
         nodes.add(pred);
      
      
      while (!nodes.isEmpty())
      {
         LLVMCFGNode node = nodes.remove();
         
         
         if (visited.contains(node))
            continue;
         
         visited.add(node);
         
         
         if (node.predecessors.size() == 1 && node.loopback == null)
         {
            LLVMCFGNode pred = node.predecessors.get(0);
            
            if (pred.link instanceof LLVMJump)
            {
               pred.instructions.addAll(node.instructions);
               pred.link = node.link;
               
               if (node.link instanceof LLVMJump)
               {
                  ((LLVMJump)node.link).target.replacePredecessor(node, pred);
               }
               else
               {
                  ((LLVMBranch)node.link).thenNode
                        .replacePredecessor(node, pred);
                  
                  ((LLVMBranch)node.link).elseNode
                        .replacePredecessor(node, pred);
               }
            }
         }
         
         
         for (LLVMCFGNode pred : node.predecessors)
            nodes.add(pred);
         
         if (node.loopback != null)
            nodes.add(node.loopback);
      }
   }
   
   
   private void replaceLink(LLVMCFGNode from, LLVMCFGNode to)
   {
      boolean isloop = (from.link instanceof LLVMJump)
            && ((LLVMJump)from.link).loop;
      
      
      if (this.link instanceof LLVMJump)
      {
         this.link = new LLVMJump(to, isloop);
      }
      else
      {
         LLVMBranch branch = ((LLVMBranch)this.link);
         
         LLVMCFGNode thenNode = branch.thenNode;
         LLVMCFGNode elseNode = branch.elseNode;
         
         
         if (thenNode.equals(to) || elseNode.equals(to))
            this.link = new LLVMJump(to, isloop);
         
         else if (thenNode.equals(from))
            this.link = new LLVMBranch(branch.guard, isloop, to, elseNode);
         
         else
            this.link = new LLVMBranch(branch.guard, isloop, thenNode, to);
      }
      
      
      to.replacePredecessor(from, this);
   }
   
   
   private void replacePredecessor(LLVMCFGNode from, LLVMCFGNode to)
   {
      if (this.loopback != null && this.loopback.equals(from))
      {
         this.loopback = to;
      }
      else
      {
         this.predecessors.remove(from);
         
         if (to != null)
            this.predecessors.add(to);
      }
   }
   
   
   public void setUID()
   {
      if (this.uid == -1)
         this.uid = count++;
   }
   
   
   public int getUID()
   {
      return this.uid;
   }
   
   
   public String llvmString()
   {
      return 'N' + Integer.toString(this.uid);
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      if (this.uid != -1)
      {
         printer.print(this.llvmString());
         printer.println(':');
      }
      
      
      for (LLVMInstruction instruction : this.instructions)
      {
         printer.print("   ");
         printer.println(instruction.llvmString());
      }
      
      
      if (this.link != null)
         this.link.writeLLVM(printer);
   }
   
   
   public ARMCFGNode buildARM()
   {
      for (LLVMInstruction instruction : this.instructions)
         instruction.buildARM(armNode);
      
      
      if (this.link != null)
         this.link.buildARM(this.armNode);
      
      
      return armNode;
   }
   
   
   public void setupARMNode()
   {
      this.armNode = new ARMCFGNode();
      
      if (this.uid != -1)
         this.armNode.setUID();
   }
   
   
   public ARMCFGNode armNode()
   {
      return this.armNode;
   }
}
