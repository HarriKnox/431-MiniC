package llvm.instruction.targeted;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMLoad extends LLVMTargetedInstruction
{
   public final LLVMValue source;
   
   
   public LLVMLoad(LLVMValue source)
   {
      super(source.type);
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder("load ")
            .append(this.source.type.llvmString())
            .append("* ")
            .append(this.source.llvmString())
            .toString();
   }
}
