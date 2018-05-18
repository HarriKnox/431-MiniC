package llvm.instruction.targeted.arithmetic;


import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMIntType;

import llvm.value.operand.LLVMOperand;


public abstract class LLVMArithmetic extends LLVMTargetedInstruction
{
   public final LLVMOperand left;
   public final LLVMOperand right;
   
   
   public LLVMArithmetic(LLVMOperand left, LLVMOperand right)
   {
      super(new LLVMIntType());
      
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder(this.getOperation())
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
