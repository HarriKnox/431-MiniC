package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMsle extends LLVMComparison
{
   public LLVMsle(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sle";
   }
}
