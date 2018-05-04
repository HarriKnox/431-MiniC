package llvm.instruction.targeted;


import llvm.instruction.LLVMInstruction;

import llvm.value.variable.LLVMVariable;


public abstract class LLVMTargetedInstruction extends LLVMInstruction
{
   public final LLVMVariable target;
   
   
   public LLVMTargetedInstruction(LLVMVariable target)
   {
      this.target = target;
   }
}
