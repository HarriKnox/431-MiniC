package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.binary.ARMAdd;


public class LLVMadd extends LLVMArithmetic
{
   public LLVMadd(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "add";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMAdd(
            this.target.buildARM(node),
            this.left.buildARM(node),
            this.right.buildARM(node)));
   }
}
