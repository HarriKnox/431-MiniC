package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMne extends LLVMComparison
{
   public LLVMne(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "ne";
   }
}
