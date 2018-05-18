package llvm.link;


import llvm.value.operand.LLVMOperand;


public class LLVMRet extends LLVMLink
{
   public final LLVMOperand value;
   
   
   public LLVMRet(LLVMOperand value)
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
