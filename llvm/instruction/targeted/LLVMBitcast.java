package llvm.instruction.targeted;


import llvm.type.LLVMType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMBitcast extends LLVMTargetedInstruction
{
   public final LLVMVariable source;
   
   
   public LLVMBitcast(LLVMVariable source, LLVMType targetType)
   {
      super(targetType);
      this.source = source;
   }
}
