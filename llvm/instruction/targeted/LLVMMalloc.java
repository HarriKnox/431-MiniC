package llvm.instruction.targeted;


import java.util.Collections;

import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;

import llvm.value.operand.constant.LLVMInt;


public class LLVMMalloc extends LLVMCall
{
   public LLVMMalloc(int fields)
   {
      super("malloc",
            new LLVMPointerType(new LLVMByteType()),
            Collections.singletonList(new LLVMInt(fields * 4)));
   }
}
