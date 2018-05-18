package llvm.value.operand.constant;


import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


public abstract class LLVMConstant extends LLVMOperand
{
   public LLVMConstant(LLVMType type)
   {
      super(type);
   }
}
