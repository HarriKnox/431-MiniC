package llvm.value.operand;


import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public abstract class LLVMOperand extends LLVMValue
{
   public LLVMOperand(LLVMType type)
   {
      super(type);
   }
}
