package llvm.value.variable;


import llvm.type.LLVMType;

import llvm.value.LLVMValue;


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
}
