package llvm.instruction.arithmetic;


import llvm.instruction.LLVMInstruction;

import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public abstract class LLVMArithmetic extends LLVMInstruction
{
   public final LLVMValue left;
   public final LLVMValue right;
   
   
   public LLVMArithmetic(left, right)
   {
      super(new LLVMRegister(left.type));
      
      this.left = left;
      this.right = right;
   }
}
