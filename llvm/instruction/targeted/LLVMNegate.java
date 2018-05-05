package llvm.instruction.targeted;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMNegate extends LLVMTargetedInstruction
{
   public final LLVMValue source;
   
   
   public LLVMNegate(LLVMValue source)
   {
      super(source.type);
      
      this.source = source;
   }
}
