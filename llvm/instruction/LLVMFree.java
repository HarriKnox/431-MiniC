package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMFree extends LLVMInstruction
{
   public final LLVMVariable pointer;
   
   
   public LLVMFree(LLVMVariable pointer)
   {
      super(new LLVMRegister(new LLVMVoidType()));
      
      
      this.pointer = pointer;
   }
}
