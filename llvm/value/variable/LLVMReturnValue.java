package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMReturnValue extends LLVMVariable
{
   public final String functionName;
   
   
   public LLVMReturnValue(String funcName, LLVMType type)
   {
      super(type);
      
      this.functionName = funcName);
   }
   
   
   /* "%" + this.functionName + ".return.value" */
}
