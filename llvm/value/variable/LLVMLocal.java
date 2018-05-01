package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMLocal extends LLVMVariable
{
   public final String functionName;
   public final String identifier;
   
   
   public LLVMLocal(String funcName, String id, LLVMType type)
   {
      super(type);
      
      this.functionName = funcName;
      this.identifier = id;
   }
   
   
   /* '%' + this.functionName + '.' + this.identifier */
}
