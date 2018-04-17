package cfg;


import java.util.LinkedList;
import java.util.List;


class CFGNode
{
   List<CFGNode> predecesors;
   List<CFGNode> successors;
   List<LLVMInstruction> instructions;
   
   
   CFGNode()
   {
      this.predecesors = new LinkedList<>();
      this.successors = new LinkedList<>();
      this.instructions = new LinkedList<>();
   }
   
   
   void link(CFGNode descendent)
   {
      this.successors.add(descendent);
      descendent.predecesors.add(this);
   }
   
   
   void addInstruction(LLVMInstruction instr)
   {
      this.instructions.add(instr);
   }
}
