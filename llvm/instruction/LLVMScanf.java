package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMGlobal;
import llvm.value.variable.LLVMRegister;


public class LLVMScanf extends LLVMInstruction
{
   public LLVMScanf()
   {
      super(new LLVMRegister(new LLVMVoidType()));
   }
}
