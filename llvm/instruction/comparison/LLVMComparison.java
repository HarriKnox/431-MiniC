package llvm.instruction.comparison;


import llvm.instruction.LLVMInstruction;

import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public abstract class LLVMComparison extends LLVMInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMComparison(left, right)
   {
      super(new LLVMRegister(left.type));
      
      this.left = left;
      this.right = right;
   }
}
