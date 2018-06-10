package llvm.type;


import llvm.value.operand.constant.LLVMConstant;


public class LLVMPointerType extends LLVMType
{
   public final LLVMType type;
   
   
   public LLVMPointerType(LLVMType type)
   {
      this.type = type;
   }
   
   
   @Override
   public boolean equivalent(LLVMType t)
   {
      return (t instanceof LLVMPointerType)
            && ((LLVMPointerType)t).type.equivalent(this.type);
   }
   
   
   @Override
   public String astString()
   {
      return this.type.astString() + " pointer";
   }
   
   
   @Override
   public String llvmString()
   {
      return this.type.llvmString() + '*';
   }
   
   
   @Override
   public LLVMConstant defaultValue()
   {
      return this.type.defaultValue();
   }
}
