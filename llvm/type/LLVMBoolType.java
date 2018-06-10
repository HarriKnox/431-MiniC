package llvm.type;


import llvm.value.operand.constant.LLVMBool;
import llvm.value.operand.constant.LLVMConstant;


public class LLVMBoolType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMBoolType;
   }
   
   
   @Override
   public String astString()
   {
      return "bool";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i1";
   }
   
   
   @Override
   public LLVMConstant defaultValue()
   {
      return new LLVMBool(false);
   }
}
