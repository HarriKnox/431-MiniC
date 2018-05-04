package llvm.instruction.comparison;


import llvm.instruction.LLVMInstruction;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public abstract class LLVMComparison extends LLVMInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMComparison(LLVMValue left, LLVMValue right)
   {
      super(new LLVMRegister(left.type));
      
      this.left = left;
      this.right = right;
   }
}
