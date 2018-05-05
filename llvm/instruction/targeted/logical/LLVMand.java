package llvm.instruction.targeted.logical;


import llvm.value.LLVMValue;


public class LLVMand extends LLVMLogical
{
   public LLVMand(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "and";
   }
}
