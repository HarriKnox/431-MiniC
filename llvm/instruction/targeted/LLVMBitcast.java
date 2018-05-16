package llvm.instruction.targeted;


import llvm.type.LLVMType;

import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMVariable;


public class LLVMBitcast extends LLVMTargetedInstruction
{
   public final LLVMVariable source;
   
   
   public LLVMBitcast(LLVMVariable source, LLVMType targetType)
   {
      super(targetType);
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder("bitcast ")
            .append(this.source.llvmTypedString())
            .append(" to ")
            .append(this.target.type.llvmString())
            .toString();
   }
   
   /*
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      return this.source.buildARM(node);
   }*/
}
