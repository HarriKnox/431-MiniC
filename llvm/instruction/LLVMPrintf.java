package llvm.instruction;


import java.util.Arrays;

import llvm.type.LLVMStdioType;

import llvm.value.operand.LLVMOperand;


import static llvm.value.operand.constant.LLVMStdio.PRINT_FORMAT;
import static llvm.value.operand.constant.LLVMStdio.PRINTLN_FORMAT;


public class LLVMPrintf extends LLVMCallVoid
{
   public LLVMPrintf(LLVMOperand value, boolean println)
   {
      super(
            "printf",
            new LLVMStdioType(),
            Arrays.asList(new LLVMOperand[]{
                  println ? PRINTLN_FORMAT : PRINT_FORMAT,
                  value}));
   }
}
