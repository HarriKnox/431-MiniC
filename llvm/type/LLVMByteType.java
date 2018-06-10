package llvm.type;


import llvm.value.operand.constant.LLVMConstant;
import llvm.value.operand.constant.LLVMInt;


public class LLVMByteType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMByteType;
   }
   
   
   @Override
   public String astString()
   {
      return "int";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i8";
   }
   
   
   @Override
   public LLVMConstant defaultValue()
   {
      return new LLVMInt(0);
   }
}
