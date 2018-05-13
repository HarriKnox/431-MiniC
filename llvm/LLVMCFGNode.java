package llvm;


import java.io.PrintWriter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import llvm.instruction.LLVMInstruction;

import llvm.link.LLVMBranch;
import llvm.link.LLVMJump;
import llvm.link.LLVMLink;
import llvm.link.LLVMRet;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMReturnValue;


public class LLVMCFGNode
{
   public final List<LLVMInstruction> instructions = new LinkedList<>();
   public final List<LLVMCFGNode> predecessors = new LinkedList<>();
   public LLVMCFGNode loopback = null;
   public LLVMLink link = null;
   public final boolean unreachable;
   
   
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
   
   
   public void branch(LLVMValue guard,
         LLVMCFGNode thenNode, LLVMCFGNode elseNode)
   {
      thenNode.predecessors.add(this);
      elseNode.predecessors.add(this);
      
      this.link = new LLVMBranch(guard, thenNode, elseNode);
   }
   
   
   public void ret(LLVMValue value)
   {
      this.link = new LLVMRet(value);
   }
   
   
   public void recursivisit(List<LLVMCFGNode> nodes)
   {
      if (nodes.contains(this))
         return;
      
      
      for (LLVMCFGNode parent : this.predecessors)
         parent.recursivisit(nodes);
      
      
      if (!nodes.contains(this))
      {
         nodes.add(this);
         this.getUID();
      }
      
      
      if (this.loopback != null)
         this.loopback.recursivisit(nodes);
   }
   
   
   public LLVMCFGNode cleanCFG()
   {
      removeUnreachables();
      
      removeEmpties();
      
      
      /* Remove unnecessary jump if possible */
      if (this.predecessors.size() == 1)
      {
         LLVMCFGNode pred = this.predecessors.get(0);
         
         if (pred.link instanceof LLVMJump)
         {
            pred.instructions.addAll(this.instructions);
            pred.link = this.link;
            
            return pred.cleanCFG();
         }
      }
      
      
      for (LLVMCFGNode pred : this.predecessors)
         pred.cleanCFG();
      
      return this;
   }
   
   
   private void removeUnreachables()
   {
      Iterator<LLVMCFGNode> predecessorator = this.predecessors.iterator();
      
      
      /* Remove all unreachable predecessors */
      while (predecessorator.hasNext())
         if (predecessorator.next().unreachable)
            predecessorator.remove();
      
      
      /* Remove the loopback if it won't be reached */
      if (loopback != null && loopback.unreachable)
         this.loopback = null;
   }
   
   
   private void removeEmpties()
   {
      List<LLVMCFGNode> newPredecessors = new LinkedList<>();
      
      
      while (true)
      {
         Iterator<LLVMCFGNode> predecessorator = this.predecessors.iterator();
         
         
         /* Remove all empty predecessors */
         while (predecessorator.hasNext())
         {
            LLVMCFGNode pred = predecessorator.next();
            
            if (pred.instructions.isEmpty() && (pred.link instanceof LLVMJump))
            {
               for (LLVMCFGNode predepredecessor : pred.predecessors)
               {
                  predepredecessor.replaceLink(pred, this);
                  
                  if (!newPredecessors.contains(predepredecessor))
                     newPredecessors.add(predepredecessor);
               }
            }
            else
            {
               newPredecessors.add(pred);
            }
         }
         
         
         /* If the two lists equal, then nothing changed and we can break */
         if (this.predecessors.equals(newPredecessors))
            break;
         
         
         /* Otherwise, move the contents from the new list and restart */
         this.predecessors.clear();
         this.predecessors.addAll(newPredecessors);
         
         newPredecessors.clear();
      }
   }
   
   
   private void replaceLink(LLVMCFGNode from, LLVMCFGNode to)
   {
      if (this.link instanceof LLVMJump)
      {
         this.link = new LLVMJump(to);
         return;
      }
      
      
      LLVMBranch branch = ((LLVMBranch)this.link);
      
      LLVMCFGNode thenNode = branch.thenNode;
      LLVMCFGNode elseNode = branch.elseNode;
      
      
      if (thenNode.equals(to) || elseNode.equals(to))
         this.link = new LLVMJump(to);
      
      else if (thenNode.equals(from))
         this.link = new LLVMBranch(branch.guard, to, elseNode);
      
      else
         this.link = new LLVMBranch(branch.guard, thenNode, to);
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public String llvmString()
   {
      return 'N' + Integer.toString(this.getUID());
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      printer.println(this.llvmString() + ':');
      
      for (LLVMInstruction instruction : this.instructions)
      {
         printer.print("   ");
         printer.println(instruction.llvmString());
      }
      
      if (this.link != null)
      {
         printer.print("   ");
         printer.println(this.link.llvmString());
      }
   }
}
