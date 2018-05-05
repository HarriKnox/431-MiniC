package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMslt extends LLVMComparison
{
   public LLVMslt(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "slt";
   }
}
