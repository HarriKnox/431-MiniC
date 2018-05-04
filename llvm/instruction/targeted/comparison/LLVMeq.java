package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMeq extends LLVMComparison
{
   public LLVMeq(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
}
