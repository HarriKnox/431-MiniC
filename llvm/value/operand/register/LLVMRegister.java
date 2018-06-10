package llvm.value.operand.register;


import llvm.value.operand.LLVMOperand;

import llvm.type.LLVMType;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public abstract class LLVMRegister extends LLVMOperand
{
   private LLVMOperand binding = null;
   
   
   public LLVMRegister(LLVMType type)
   {
      super(type);
   }
   
   
   public void bind(LLVMOperand binding)
   {
      this.binding = binding;
   }
   
   
   @Override
   public String llvmString()
   {
      if (this.binding == null)
         return this.regLLVMString();
      
      return this.binding.llvmString();
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      if (this.binding == null)
         return this.regBuildARM(node);
      
      return this.binding.buildARM(node);
   }
   
   
   protected abstract String regLLVMString();
   
   protected abstract ARMRegister regBuildARM(ARMCFGNode node);
}
