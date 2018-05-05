package llvm.instruction;


import java.util.Collections;

import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMVariable;


public class LLVMFree extends LLVMCallVoid
{
   public LLVMFree(LLVMVariable pointer)
   {
      super("free", new LLVMVoidType(), Collections.singletonList(pointer));
   }
}
