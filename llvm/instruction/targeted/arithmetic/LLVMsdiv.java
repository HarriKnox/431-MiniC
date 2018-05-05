package llvm.instruction.targeted.arithmetic;


import llvm.value.LLVMValue;


public class LLVMsdiv extends LLVMArithmetic
{
   public LLVMsdiv(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sdiv";
   }
}
