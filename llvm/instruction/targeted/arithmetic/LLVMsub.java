package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.binary.ARMSub;


public class LLVMsub extends LLVMArithmetic
{
   public LLVMsub(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sub";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMSub(
            this.target.buildARM(node),
            this.left.buildARM(node),
            this.right.buildARM(node)));
   }
}
