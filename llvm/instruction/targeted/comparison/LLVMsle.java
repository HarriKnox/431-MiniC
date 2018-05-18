package llvm.instruction.targeted.comparison;


import llvm.value.operand.LLVMOperand;


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
   
   /*
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMov mov = new ARMMov(new ARMInt(0));
      ARMCmp cmp = new ARMCmp(leftReg, rightReg);
      ARMMovle movle = new ARMMovle(mov.target, new ARMInt(1));
      
      node.add(mov).add(cmp).add(movle);
      
      return mov.target;
   }*/
}
