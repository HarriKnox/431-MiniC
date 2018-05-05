package llvm.instruction.targeted.arithmetic;


import llvm.value.LLVMValue;


public class LLVMmul extends LLVMArithmetic
{
   public LLVMmul(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "mul";
   }
}
