package llvm.instruction.targeted;


import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.binary.ARMRsb;

import arm.value.operand.ARMConstant;


public class LLVMNegate extends LLVMTargetedInstruction
{
   public final LLVMOperand source;
   
   
   public LLVMNegate(LLVMOperand source)
   {
      super(source.type);
      
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return "sub i32 0, " + this.source.llvmString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMRsb(this.source.buildARM(node), new ARMConstant(0)));
   }
}
