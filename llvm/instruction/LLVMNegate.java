package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMNegate extends LLVMInstruction
{
   public final LLVMValue source;
   
   
   public LLVMNegate(LLVMValue source)
   {
      super(new LLVMRegister(source.type));
      
      this.source = source;
   }
}
