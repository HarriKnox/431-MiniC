package llvm.value.operand;


import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public abstract class LLVMOperand extends LLVMValue
{
   public LLVMOperand(LLVMType type)
   {
      super(type);
   }
   
   public abstract ARMRegister buildARM(ARMCFGNode node);
}
