package llvm.value.constant;


import llvm.type.LLVMBoolType;
import llvm.type.LLVMType;


public class LLVMBool extends LLVMConstant
{
   public final boolean value;
   
   
   public LLVMBool(boolean value)
   {
      super(new LLVMBoolType());
      
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return this.value ? "true" : "false";
   }
}
