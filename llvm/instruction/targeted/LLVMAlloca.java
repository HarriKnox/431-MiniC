package llvm.instruction.targeted;


import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMAlloca extends LLVMTargetedInstruction
{
   public LLVMAlloca(LLVMVariable pointer)
   {
      super(pointer);
   }
}
