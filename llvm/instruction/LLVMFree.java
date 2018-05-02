package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMRegister;


public class LLVMFree extends LLVMInstruction
{
   public final LLVMRegister pointer;
   
   
   public LLVMFree(LLVMRegister pointer)
   {
      super(new LLVMRegister(new LLVMVoidType()));
      
      
      this.pointer = pointer;
   }
}
