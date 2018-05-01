package llvm.instruction;


import llvm.type.LLVMPointerType;

import llvm.value.LLVMRegister;


public class LLVMMalloc extends LLVMInstruction
{
   public final LLVMRegister target;
   public final int size;
   
   
   public LLVMMalloc(int numberOfFields)
   {
      this.target = new LLVMRegister(new LLVMPointerType());
      this.size = numberOfFields * 4;
   }
}
