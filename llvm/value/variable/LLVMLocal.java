package llvm.value.variable;


import llvm.type.LLVMType;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public class LLVMLocal extends LLVMVariable
{
   public final String function;
   public final String identifier;
   public final int index;
   
   
   public LLVMLocal(String function,
         String identifier, LLVMType type, int index)
   {
      super(type);
      
      this.function = function;
      this.identifier = identifier;
      this.index = index;
   }
   
   
   @Override
   public String llvmString()
   {
      return '%' + this.function + '.' + this.identifier;
   }
   
   
   @Override
   public ARMAddress buildARM(ARMCFGNode node)
   {
      return new ARMAddress(SP, this.index * 4);
   }
}
