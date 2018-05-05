package llvm.instruction.targeted.arithmetic;


import llvm.value.LLVMValue;


public class LLVMsub extends LLVMArithmetic
{
   public LLVMsub(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sub";
   }
}
