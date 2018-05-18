package llvm.instruction;


import java.util.Collections;

import llvm.type.LLVMVoidType;

import llvm.value.operand.register.LLVMVirtual;


public class LLVMFree extends LLVMCallVoid
{
   public LLVMFree(LLVMVirtual pointer)
   {
      super("free", new LLVMVoidType(), Collections.singletonList(pointer));
   }
}
