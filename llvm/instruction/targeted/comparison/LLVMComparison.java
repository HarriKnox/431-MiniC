package llvm.instruction.targeted.comparison;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMBoolType;

import llvm.value.operand.LLVMOperand;


public abstract class LLVMComparison extends LLVMTargetedInstruction
{
   public final LLVMOperand left;
   public final LLVMOperand right;
   
   
   public LLVMComparison(LLVMOperand left, LLVMOperand right)
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
            .append(this.left.type.llvmString())
            .append(' ')
            .append(this.left.llvmString())
            .append(", ")
            .append(this.right.llvmString())
            .toString();
   }
   
   
   protected abstract String getOperation();
}
