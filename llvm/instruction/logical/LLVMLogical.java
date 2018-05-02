package llvm.instruction.logical;


import llvm.instruction.LLVMInstruction;

import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public abstract class LLVMLogical extends LLVMInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMLogical(left, right)
   {
      super(new LLVMRegister(left.type));
      
      this.left = left;
      this.right = right;
   }
}
