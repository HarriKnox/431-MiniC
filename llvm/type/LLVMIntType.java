package llvm.type;


import llvm.value.operand.constant.LLVMConstant;
import llvm.value.operand.constant.LLVMInt;


public class LLVMIntType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMIntType;
   }
   
   
   @Override
   public String astString()
   {
      return "int";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i32";
   }
   
   
   @Override
   public LLVMConstant defaultValue()
   {
      return new LLVMInt(0);
   }
}
