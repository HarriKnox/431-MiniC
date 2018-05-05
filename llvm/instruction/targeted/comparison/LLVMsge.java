package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMsge extends LLVMComparison
{
   public LLVMsge(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sge";
   }
}
