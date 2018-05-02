package llvm.instruction;


import llvm.type.LLVMPointerType;

import llvm.value.LLVMRegister;


public class LLVMMalloc extends LLVMInstruction
{
   public final int size;
   
   
   public LLVMMalloc(int numberOfFields)
   {
      super(new LLVMRegister(new LLVMPointerType()));
      this.size = numberOfFields * 4;
   }
}
