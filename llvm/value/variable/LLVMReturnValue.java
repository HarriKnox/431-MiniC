package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMReturnValue extends LLVMLocal
{
   public LLVMReturnValue(String funcName, LLVMType type)
   {
      super(funcName, "return.value", type);
   }
   
   
   /* "%" + this.functionName + ".return.value" */
}
