package arm;


import arm.instruction.ARMInstruction;


public class ARMCFGNode
{
   public final List<ARMInstruction> instructions = new LinkedList<>();
   public final List<ARMCFGNode> predecessors = new LinkedList<>();
   public ARMCFGNode loopback = null;
   /*public ARMLink link = null;*/
   
   public final int uid;
   
   
   public ARMCFGNode(int uid)
   {
      this.uid = uid;
   }
   
   
   public ARMCFGNode add(ARMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
}
