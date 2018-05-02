package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMStore extends LLVMInstruction
{
   public final LLVMVariable destination;
   public final LLVMValue source;
   
   
   public LLVMStore(LLVMVariable destination, LLVMValue source)
   {
      super(new LLVMRegister(new LLVMVoidType()));
      
      this.destination = destination;
      this.source = source;
   }
}
