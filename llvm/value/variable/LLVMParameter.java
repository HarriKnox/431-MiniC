package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMParameter extends LLVMLocal
{
   public LLVMParameter(String function,
         String identifier, LLVMType type, int index)
   {
      super(function, "param." + identifier, type, index);
   }
}
