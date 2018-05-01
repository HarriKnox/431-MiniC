package llvm.value.constant;


import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public abstract class LLVMConstant extends LLVMValue
{
   public LLVMConstant(LLVMType type)
   {
      super(type);
   }
}
