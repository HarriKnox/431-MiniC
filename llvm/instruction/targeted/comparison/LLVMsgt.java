package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMsgt extends LLVMComparison
{
   public LLVMsgt(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sgt";
   }
}
