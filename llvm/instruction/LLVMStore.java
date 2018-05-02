package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMStore extends LLVMInstruction
{
   public final LLVMValue source;
   
   
   public LLVMStore(LLVMVariable target, LLVMValue source)
   {
      super(target);
      
      this.source = source;
   }
}
