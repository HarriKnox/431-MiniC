package llvm.value.variable;


import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;


import static arm.value.operand.ARMRegister.FP;


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
      if (this.type instanceof LLVMVoidType)
         return null;
      
      return new ARMAddress(FP, (this.index * -4) - 8);
   }
}
