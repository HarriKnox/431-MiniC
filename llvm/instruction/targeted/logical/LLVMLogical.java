package llvm.instruction.targeted.logical;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMBoolType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMLogical extends LLVMTargetedInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMLogical(LLVMValue left, LLVMValue right)
   {
      super(new LLVMBoolType());
      
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder(this.getOperation())
            .append(" i1 ")
            .append(this.left.llvmString())
            .append(", ")
            .append(this.right.llvmString())
            .toString();
   }
   
   
   protected abstract String getOperation();
}
