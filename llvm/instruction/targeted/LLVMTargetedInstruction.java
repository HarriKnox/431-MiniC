package llvm.instruction.targeted;


import llvm.instruction.LLVMInstruction;

import llvm.type.LLVMType;

import llvm.value.operand.register.LLVMVirtual;


public abstract class LLVMTargetedInstruction extends LLVMInstruction
{
   public final LLVMVirtual target;
   
   
   public LLVMTargetedInstruction(LLVMType type)
   {
      this.target = new LLVMVirtual(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return this.target.llvmString() + " = " + this.getInstruction();
   }
   
   
   protected abstract String getInstruction();
}
