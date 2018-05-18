package llvm.value.operand.register;


import llvm.type.LLVMType;


public class LLVMParameter extends LLVMRegister
{
   public final String function;
   public final String id;
   public final int index;
   
   
   public LLVMParameter(String function,
         String identifier, LLVMType type, int index)
   {
      super(type);
      
      this.function = function;
      this.id = identifier;
      this.index = index;
   }
   
   
   @Override
   public String llvmString()
   {
      return '%' + this.function + ".param." + this.id;
   }
}
