package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMParameter extends LLVMLocal
{
   public LLVMParameter(String function, String identifier, LLVMType type)
   {
      super(function, String.format("param.%s", identifier),type);
   }
}
