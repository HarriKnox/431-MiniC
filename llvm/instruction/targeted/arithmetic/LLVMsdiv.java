package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.ARMBl;

import arm.instruction.mov.ARMMov;

import arm.value.operand.ARMRegister;


public class LLVMsdiv extends LLVMArithmetic
{
   public LLVMsdiv(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sdiv";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node  .add(new ARMMov(new ARMRegister(0), this.left.buildARM(node)))
            .add(new ARMMov(new ARMRegister(1), this.right.buildARM(node)))
            .add(new ARMBl("__aeabi_idiv"))
            .add(new ARMMov(this.target.buildARM(node), new ARMRegister(0)));
   }
}
