package llvm.link;


import llvm.value.LLVMValue;


public class LLVMRet extends LLVMLink
{
   public final LLVMValue value;
   
   
   public LLVMRet(LLVMValue value)
   {
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return "ret " + ((this.value == null)
            ? "void"
            : this.value.llvmTypedString());
   }
}
