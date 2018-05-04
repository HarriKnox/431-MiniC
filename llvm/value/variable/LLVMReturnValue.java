package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMReturnValue extends LLVMLocal
{
   public LLVMReturnValue(String function, LLVMType type)
   {
      super(function, "return.value", type);
   }
}
