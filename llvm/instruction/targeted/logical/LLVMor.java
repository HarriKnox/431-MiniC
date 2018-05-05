package llvm.instruction.targeted.logical;


import llvm.value.LLVMValue;


public class LLVMor extends LLVMLogical
{
   public LLVMor(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "or";
   }
}
