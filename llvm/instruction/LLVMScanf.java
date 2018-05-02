package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.LLVMRegister;
import llvm.value.LLVMReadScratch;


public class LLVMScanf extends LLVMInstruction
{
   public LLVMScanf()
   {
      super(new LLVMRegister(new LLVMVoidType()));
   }
}
