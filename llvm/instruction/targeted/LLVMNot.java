package llvm.instruction.targeted;


import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMNot extends LLVMTargetedInstruction
{
   public final LLVMValue source;
   
   
   public LLVMNot(LLVMValue source)
   {
      super(source.type);
      
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return "xor i1 true, " + this.source.llvmString();
   }
}
