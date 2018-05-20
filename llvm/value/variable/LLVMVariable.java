package llvm.value.variable;


import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;


public abstract class LLVMVariable extends LLVMValue
{
   public LLVMVariable(LLVMType type)
   {
      super(type);
   }
   
   
   public String llvmTypedString()
   {
      return this.type.llvmString() + "* " + this.llvmString();
   }
   
   
   public abstract ARMAddress buildARM(ARMCFGNode node);
}
