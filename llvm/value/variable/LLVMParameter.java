package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMParameter extends LLVMVariable
{
   public final String functionName;
   public final String identifier;
   
   
   public LLVMParameter(String funcName, String id, LLVMType type)
   {
      super(type);
      
      this.functionName = funcName;
      this.identifier = id;
   }
   
   
   /* '%' + this.functionName + '.param.' + this.identifier */
}
