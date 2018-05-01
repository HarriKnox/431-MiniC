package llvm.instruction;


import llvm.value.LLVMRegister;


public class LLVMLoad extends LLVMInstruction
{
   public final LLVMRegister target;
   public final LLVMValue source;
   
   
   public LLVMLoad(LLVMValue source)
   {
      this.target = new LLVMRegister(source.type);
      this.source = source;
   }
}
