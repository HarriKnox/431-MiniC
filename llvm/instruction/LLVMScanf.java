package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMReadScratch;


public class LLVMScanf extends LLVMInstruction
{
   public LLVMScanf()
   {
      super(new LLVMRegister(new LLVMVoidType()));
   }
}
