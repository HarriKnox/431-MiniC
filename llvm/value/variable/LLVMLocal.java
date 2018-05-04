package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMLocal extends LLVMVariable
{
   public final String function;
   public final String identifier;
   
   
   public LLVMLocal(String function, String identifier, LLVMType type)
   {
      super(type);
      
      this.function = function;
      this.identifier = identifier;
   }
   
   
   @Override
   public String llvmString()
   {
      return String.format("%%%s.%s", this.function, this.identifier);
   }
}
