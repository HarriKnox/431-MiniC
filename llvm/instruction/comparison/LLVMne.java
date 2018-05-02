package llvm.instruction.comparison;


import llvm.value.LLVMValue;


public class LLVMne extends LLVMComparison
{
   public LLVMne(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
}
