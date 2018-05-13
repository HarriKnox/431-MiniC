package llvm.instruction.targeted.logical;


import llvm.value.LLVMValue;


public class LLVMand extends LLVMLogical
{
   public LLVMand(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "and";
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMAnd and = new ARMAnd(leftReg, rightReg);
      
      node.add(and);
      
      return and.target;
   }
}
