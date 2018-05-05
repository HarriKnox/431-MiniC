package llvm.instruction.targeted;


import llvm.instruction.LLVMInstruction;

import llvm.type.LLVMType;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMTargetedInstruction extends LLVMInstruction
{
   public final LLVMRegister target;
   
   
   public LLVMTargetedInstruction(LLVMType type)
   {
      this.target = new LLVMRegister(type);
   }
}
