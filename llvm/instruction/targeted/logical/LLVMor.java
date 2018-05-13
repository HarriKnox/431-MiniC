package llvm.instruction.targeted.logical;


import llvm.value.LLVMValue;


public class LLVMor extends LLVMLogical
{
   public LLVMor(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "or";
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMOrr orr = new ARMOrr(leftReg, rightReg);
      
      node.add(orr);
      
      return orr.target;
   }
}
