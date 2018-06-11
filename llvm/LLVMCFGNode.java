package llvm;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import llvm.instruction.LLVMInstruction;

import llvm.link.LLVMBranch;
import llvm.link.LLVMJump;
import llvm.link.LLVMLink;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.register.LLVMPhi;

import llvm.value.variable.LLVMLocal;

import arm.ARMCFGNode;

import common.Printer;
import common.Options;


public class LLVMCFGNode
{
   public final List<LLVMInstruction> instructions = new LinkedList<>();
   public final List<LLVMCFGNode> predecessors = new LinkedList<>();
   public LLVMCFGNode loopback = null;
   public LLVMLink link = null;
   public final boolean unreachable;
   
   
   /* For SSA */
   private final Map<LLVMLocal, LLVMOperand> currentDefs = new HashMap<>();
   public boolean sealed = true; /* Most nodes will be sealed */
   private final List<LLVMPhi> phis = new LinkedList<>();
   
   
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
   
   
   public void recursivisit(List<LLVMCFGNode> nodes, Options opts)
   {
      /* Return if we already visited this node */
      if (nodes.contains(this))
         return;
      
      
      /* Visit predecessors first */
      for (LLVMCFGNode parent : this.predecessors)
         parent.recursivisit(nodes, opts);
      
      
      /* Visit this node, but not if we've already visited it in a loop */
      if (!nodes.contains(this))
         this.visit(nodes, opts);
      
      
      /* Visit the loopback after visiting this node */
      if (this.loopback != null)
         this.loopback.recursivisit(nodes, opts);
   }
   
   
   private void visit(List<LLVMCFGNode> nodes, Options opts)
   {
      /*
       * Don't set the node's ID if
       *  - the node is the first node, and
       *  - the node has no predecessors (including loopback), and
       *  - either:
       *     - we're using stack-based variable storage
       *     - the node has zero successors
       *     - the node has one successor that has other predecessors
       *
       * If any of those fail, set the ID. Setting the ID is used for printing
       * the label to the node in LLVM.
       *
       * If nodes is empty, then this is the first node because we would have
       * visited the predecessors and added them to the list before visiting
       * this.
       *
       * When checking that the successor has other predecessors, we establish
       * that this node is the first node, so there won't be any other
       * predecessors aside from a loopback predecessor.
       */
      if (!(nodes.isEmpty() && (this.loopback == null)
            && (opts.stack
                  || (this.link == null)
                  || ((this.link instanceof LLVMJump)
                        && ((LLVMJump)this.link).target.loopback != null))))
         this.setUID();
      
      
      for (LLVMPhi phi : this.phis)
         this.addPhiOperands(phi);
      
      
      nodes.add(this);
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
               && !((LLVMJump)node.link).loop
               && node.currentDefs.isEmpty())
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
         
         
         /* If there is exactly one total predecessor that is not a loopback */
         if (node.predecessors.size() == 1 && node.loopback == null)
         {
            LLVMCFGNode pred = node.predecessors.get(0);
            
            
            /* If the predecessor's link is a jump */
            if (pred.link instanceof LLVMJump)
            {
               /* Move instructions up and replace the link */
               pred.instructions.addAll(node.instructions);
               pred.link = node.link;
               
               
               /* Bind the phi values to their sources from the parent */
               for (LLVMPhi phi : node.phis)
                  phi.bind(pred.readVariable(phi.variable));
               /* TODO: add node.phis to pred.phis */
               
               
               /* Add updates to SSA Local->Register Map */
               pred.currentDefs.putAll(node.currentDefs);
               
               
               /* Update successor(s)'s predecessor list */
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
   
   
   public void writeVariable(LLVMLocal variable, LLVMOperand value)
   {
      this.currentDefs.put(variable, value);
   }
   
   
   public LLVMOperand readVariable(LLVMLocal variable)
   {
      if (this.currentDefs.containsKey(variable))
         return this.currentDefs.get(variable);
      
      return this.readVariableFromPredecessors(variable);
   }
   
   
   private LLVMOperand readVariableFromPredecessors(LLVMLocal variable)
   {
      LLVMOperand val;
      
      int predCount = this.predecessorCount();
      
      
      if (!this.sealed || (predCount >= 2))
         val = createPhi(variable);
      
      else if (predCount == 0)
         val = variable.type.defaultValue();
      
      else /* if (predCount == 1) */
         val = this.predecessors.get(0).readVariable(variable);
      
      
      this.writeVariable(variable, val);
      return val;
   }
   
   
   private LLVMPhi createPhi(LLVMLocal variable)
   {
      LLVMPhi phi = new LLVMPhi(variable);
      
      this.phis.add(phi);
      
      return phi;
   }
   
   
   private void addPhiOperands(LLVMPhi phi)
   {
      LLVMLocal variable = phi.variable;
      
      for (LLVMCFGNode pred : this.predecessors)
         phi.addSource(pred, pred.readVariable(variable));
      
      if (this.loopback != null)
         phi.addSource(this.loopback, this.loopback.readVariable(variable));
   }
   
   
   private int predecessorCount()
   {
      return this.predecessors.size() + ((this.loopback == null) ? 0 : 1);
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
   
   
   public void writeLLVM(Printer printr, Options opts)
   {
      if (this.uid != -1)
         printr.print(this.llvmString()).println(':');
      
      if (!opts.stack)
      {
         for (LLVMPhi phi : this.phis)
            phi.writeLLVM(printr, opts);
      }
      
      
      for (LLVMInstruction instruction : this.instructions)
         printr.print("   ").println(instruction.llvmString());
      
      
      if (this.link != null)
         this.link.writeLLVM(printr);
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
