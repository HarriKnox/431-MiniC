package llvm.instruction.targeted.logical;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMLogical extends LLVMTargetedInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMLogical(LLVMValue left, LLVMValue right)
   {
      super(left.type);
      
      this.left = left;
      this.right = right;
   }
}
