package cfg;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;


class CFGNode
{
   static int count = 0;
   
   boolean entry;
   String label;
   List<CFGNode> predecessors;
   List<CFGNode> loopbacks;
   List<LLVMInstruction> instructions;
   LLVMBranchInstruction branch;
   
   
   CFGNode()
   {
      this.label = "LU" + count++;
      
      this.entry = false;
      this.predecessors = new LinkedList<>();
      this.loopbacks = new LinkedList<>();
      this.instructions = new LinkedList<>();
      this.branch = null;
   }
   
   CFGNode(boolean entry)
   {
      this();
      this.entry = entry;
   }
   
   
   void link(CFGNode descendent)
   {
      descendent.predecessors.add(this);
      this.branch = new LLVMJump(descendent);
   }
   
   
   void link(CFGNode guard, boolean loopback)
   {
      guard.loopbacks.add(this);
      this.branch = new LLVMJump(guard, loopback);
   }
   
   
   void link(LLVMValue condition, CFGNode thenDescendent, CFGNode elseDescendent)
   {
      thenDescendent.predecessors.add(this);
      elseDescendent.predecessors.add(this);
      this.branch = new LLVMConditional(condition, thenDescendent, elseDescendent);
   }
   
   
   void addInstruction(LLVMInstruction instr)
   {
      this.instructions.add(instr);
   }
   
   
   void addNodeTopo(Set<CFGNode> visited, List<CFGNode> toposort)
   {
      if (visited.contains(this))
         return;
      
      
      for (CFGNode parent : this.predecessors)
         parent.addNodeTopo(visited, toposort);
      
      toposort.add(this);
      visited.add(this);
      
      for (CFGNode looped : this.loopbacks)
         looped.addNodeTopo(visited, toposort);
      
      
      
      /*
      if (!visited.containsAll(this.predecessors) || visited.contains(this))
         return;
      
      visited.add(this);
         if (this.branch instanceof LLVMConditional)
         {
            LLVMConditional condBranch = (LLVMConditional)this.branch;
            condBranch.thenNode.printNode(visited);
            condBranch.elseNode.printNode(visited);
         }
         else
         {
            LLVMJump jump = (LLVMJump)this.branch;
            jump.destination.printNode(visited);
         }
      */
   }
   
   
   void printNode()
   {
      System.out.println(this.label + ":");
      
      for (LLVMInstruction instruction : this.instructions)
         System.out.println("   " + instruction.toString());
      
      if (this.branch != null)
         System.out.println("   " + this.branch.toString());
   }
}
