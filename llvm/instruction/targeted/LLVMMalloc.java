package llvm.instruction.targeted;


import java.util.Collections;

import llvm.type.LLVMPointerType;

import llvm.value.constant.LLVMInt;


public class LLVMMalloc extends LLVMCall
{
   public LLVMMalloc(int fields)
   {
      super("malloc",
            new LLVMPointerType(),
            Collections.singletonList(new LLVMInt(fields * 4)));
   }
}
