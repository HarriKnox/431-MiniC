package llvm.instruction.targeted;


import llvm.type.LLVMPointerType;

import llvm.value.variable.LLVMRegister;


public class LLVMMalloc extends LLVMTargetedInstruction
{
   public final int size;
   
   
   public LLVMMalloc(int numberOfFields)
   {
      super(new LLVMPointerType());
      this.size = numberOfFields * 4;
   }
}
