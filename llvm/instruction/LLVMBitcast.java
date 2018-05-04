package llvm.instruction;


import llvm.type.LLVMType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariabls;


public class LLVMBitcast extends LLVMInstruction
{
   public final LLVMVariable source;
   
   
   public LLVMBitcast(LLVMVariable source, LLVMType targetType)
   {
      super(new LLVMRegister(targetType));
      this.source = source;
   }
}
