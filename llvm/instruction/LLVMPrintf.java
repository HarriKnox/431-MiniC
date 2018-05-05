package llvm.instruction;


import java.util.Arrays;

import llvm.type.LLVMStdioType;

import llvm.value.LLVMValue;


import static llvm.value.constant.LLVMStdio.PRINT_FORMAT;
import static llvm.value.constant.LLVMStdio.PRINTLN_FORMAT;


public class LLVMPrintf extends LLVMCallVoid
{
   public LLVMPrintf(LLVMValue value, boolean println)
   {
      super(
            "printf",
            new LLVMStdioType(),
            Arrays.asList(new LLVMValue[]{
                  println ? PRINTLN_FORMAT : PRINT_FORMAT,
                  value}));
   }
}
