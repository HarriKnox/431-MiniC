package llvm.instruction;


import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMAlloca extends LLVMInstruction
{
   public final LLVMVariable pointer;
   
   
   public LLVMAlloca(LLVMVariable pointer)
   {
      this.pointer = pointer;
   }
}
