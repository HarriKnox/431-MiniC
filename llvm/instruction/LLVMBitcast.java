package llvm.instruction;


import llvm.type.LLVMType;

import llvm.value.LLVMRegister;


public class LLVMBitcast extends LLVMInstruction
{
   public final LLVMRegister target;
   public final LLVMRegister source;
   
   
   public LLVMBitcast(LLVMRegister source, LLVMType targetType)
   {
      this.target = new LLVMRegister(targetType);
      this.source = source;
   }
}
