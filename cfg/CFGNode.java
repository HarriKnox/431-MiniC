package cfg;


import java.util.LinkedList;
import java.util.List;


class CFGNode
{
   static int count = 0;
   
   String label;
   List<CFGNode> predecesors;
   List<LLVMInstruction> instructions;
   LLVMBranchInstruction branch;
   
   
   CFGNode()
   {
      this.label = "LU" + count++;
      
      this.predecesors = new LinkedList<>();
      this.instructions = new LinkedList<>();
      this.branch = null;
   }
   
   
   void link(CFGNode descendent)
   {
      descendent.predecesors.add(this);
      this.branch = new LLVMJump(descendent);
   }
   
   void link(LLVMRegister condition, CFGNode thenDescendent, CFGNode elseDescendent)
   {
      thenDescendent.predecessors.add(this);
      elseDescendent.predecessors.add(this);
      this.branch = new LLVMConditional(condition, thenDescendent, elseDescendent);
   }
   
   
   void addInstruction(LLVMInstruction instr)
   {
      this.instructions.add(instr);
   }
}
