package llvm.value.constant;


import llvm.type.LLVMNullType;
import llvm.type.LLVMType;


public class LLVMNull extends LLVMConstant
{
   public LLVMNull()
   {
      super(new LLVMNullType());
   }
   
   
   public LLVMNull(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "null";
   }
}
