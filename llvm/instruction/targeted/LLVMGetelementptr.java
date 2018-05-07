package llvm.instruction.targeted;


import llvm.value.LLVMValue;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMGetelementptr extends LLVMTargetedInstruction
{
   public final LLVMValue source;
   public final int index;
   
   
   public LLVMGetelementptr(LLVMValue source, LLVMType resultType, int index)
   {
      super(resultType);
      this.source = source;
      this.index = index;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder("getelementptr ")
            .append(this.source.llvmTypedString())
            .append(", i1 0, i32 ")
            .append(this.index)
            .toString();
   }
}
