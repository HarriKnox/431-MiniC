package llvm.value.operand.register;


import llvm.value.operand.LLVMOperand;

import llvm.type.LLVMType;


public abstract class LLVMRegister extends LLVMOperand
{
   public LLVMRegister(LLVMType type)
   {
      super(type);
   }
}
