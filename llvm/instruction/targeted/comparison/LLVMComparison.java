package llvm.instruction.targeted.comparison;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMBoolType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMComparison extends LLVMTargetedInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMComparison(LLVMValue left, LLVMValue right)
   {
      super(new LLVMBoolType());
      
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder("icmp ")
            .append(this.getOperation())
            .append(' ')
            .append(this.target.type.llvmString())
            .append(' ')
            .append(this.left.llvmString())
            .append(", ")
            .append(this.right.llvmString())
            .toString();
   }
   
   
   protected abstract String getOperation();
}
