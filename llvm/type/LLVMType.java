package llvm.type;


import llvm.value.operand.constant.LLVMConstant;
import llvm.value.operand.constant.LLVMNull;


public abstract class LLVMType
{
   public abstract boolean equivalent(LLVMType type);
   
   public abstract String astString();
   
   public abstract String llvmString();
   
   
   public LLVMConstant defaultValue()
   {
      return new LLVMNull();
   }
}
