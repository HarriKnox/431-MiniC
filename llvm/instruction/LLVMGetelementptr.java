package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMGetelementptr extends LLVMInstruction
{
   public final LLVMValue source;
   public final int index;
   
   
   public LLVMGetelementptr(LLVMValue source, LLVMType resultType, int index)
   {
      super(new LLVMRegister(resultType));
      this.source = source;
      this.index = index;
   }
}
