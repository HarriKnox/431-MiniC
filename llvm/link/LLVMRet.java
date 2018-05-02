package llvm.link;


import llvm.value.variable.LLVMReturnValue;


public class LLVMRet extends LLVMLink
{
   public final LLVMReturnValue returnValue;
   
   
   public LLVMRet(LLVMReturnValue returnValue)
   {
      this.returnValue = returnValue;
   }
}
