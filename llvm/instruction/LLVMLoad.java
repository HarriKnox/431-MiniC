package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMLoad extends LLVMInstruction
{
   public final LLVMValue source;
   
   
   public LLVMLoad(LLVMValue source)
   {
      super(new LLVMRegister(source.type));
      this.source = source;
   }
}