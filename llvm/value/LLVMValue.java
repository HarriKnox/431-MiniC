package llvm.value;


import llvm.type.LLVMType;


public abstract class LLVMValue
{
   public final LLVMType type;
   
   
   public LLVMValue(LLVMType type)
   {
      this.type = type;
   }
}
