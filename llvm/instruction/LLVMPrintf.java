package llvm.instruction;


import llvm.type.LLVMVoidType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMPrintf extends LLVMInstruction
{
   public final LLVMValue value;
   public final boolean println;
   
   
   public LLVMPrintf(LLVMValue value, boolean println)
   {
      super(new LLVMRegister(new LLVMVoidType()));
      
      
      this.value = value;
      this.println = println;
   }
}
