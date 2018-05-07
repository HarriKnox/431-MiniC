package llvm.instruction;


import java.util.Arrays;

import llvm.type.LLVMStdioType;

import llvm.value.LLVMValue;


import static llvm.value.constant.LLVMStdio.SCANF_FORMAT;

import static llvm.value.variable.LLVMGlobal.SCANF_SCRATCH_POINTER;


public class LLVMScanf extends LLVMCallVoid
{
   public LLVMScanf()
   {
      super(
            "scanf",
            new LLVMStdioType(),
            Arrays.asList(new LLVMValue[]{
                  SCANF_FORMAT,
                  SCANF_SCRATCH_POINTER}));
   }
}
