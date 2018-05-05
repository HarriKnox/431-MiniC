package llvm.instruction.targeted.arithmetic;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMArithmetic extends LLVMTargetedInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMArithmetic(LLVMValue left, LLVMValue right)
   {
      super(left.type);
      
      this.left = left;
      this.right = right;
   }
}
