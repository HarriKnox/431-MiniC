package llvm.instruction;


import java.util.Arrays;

import llvm.type.LLVMStdioType;

import llvm.value.operand.LLVMOperand;


import static llvm.value.operand.constant.LLVMStdio.SCANF_FORMAT;

import static llvm.value.operand.constant.LLVMScratchPointer.SCANF_SCRATCH;


public class LLVMScanf extends LLVMCallVoid
{
   public LLVMScanf()
   {
      super(
            "scanf",
            new LLVMStdioType(),
            Arrays.asList(new LLVMValue[]{
                  SCANF_FORMAT,
                  SCANF_SCRATCH}));
   }
}
