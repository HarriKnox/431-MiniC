package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;


public class LLVMsdiv extends LLVMArithmetic
{
   public LLVMsdiv(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sdiv";
   }
}
