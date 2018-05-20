package llvm.instruction.targeted.comparison;


import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.ARMCmp;

import arm.instruction.mov.ARMMov;
import arm.instruction.mov.ARMMovle;

import arm.value.operand.ARMConstant;
import arm.value.operand.ARMRegister;


public class LLVMsle extends LLVMComparison
{
   public LLVMsle(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sle";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMov mov = new ARMMov(this.target.buildARM(node), new ARMConstant(0));
      ARMCmp cmp = new ARMCmp(leftReg, rightReg);
      ARMMovle movle = new ARMMovle(mov.target, new ARMConstant(1));
      
      node.add(mov).add(cmp).add(movle);
   }
}
