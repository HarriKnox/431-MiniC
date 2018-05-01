package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMGlobal extends LLVMVariable
{
   public final String identifier;
   
   
   public LLVMGlobal(String id, LLVMType type)
   {
      super(type);
      
      this.identifier = id;
   }
   
   
   /* '@' + this.identifier */
}
