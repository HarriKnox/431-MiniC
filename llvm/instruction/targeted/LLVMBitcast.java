package llvm.instruction.targeted;


import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.mov.ARMMov;


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
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      /* A bitcast is just treating one register as another */
      this.target.setARMRegister(this.source.buildARM(node));
   }
}
