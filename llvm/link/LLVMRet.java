package llvm.link;


import llvm.value.variable.LLVMReturnValue;


public class LLVMRet extends LLVMLink
{
   public final LLVMReturnValue returnValue;
   
   
   public LLVMRet(LLVMReturnValue returnValue)
   {
      this.returnValue = returnValue;
   }
   
   
   @Override
   public String llvmString()
   {
      return "ret " + ((this.returnValue == null)
            ? "void"
            : this.returnValue.llvmTypedString());
   }
}
