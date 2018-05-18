package llvm.instruction.targeted;


import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


public class LLVMBitcast extends LLVMTargetedInstruction
{
   public final LLVMOperand source;
   
   
   public LLVMBitcast(LLVMOperand source, LLVMType targetType)
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
