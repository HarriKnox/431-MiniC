package llvm.instruction.targeted.comparison;


import llvm.value.operand.LLVMOperand;


public class LLVMsgt extends LLVMComparison
{
   public LLVMsgt(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sgt";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMov mov = new ARMMov(this.target.buildARM(node), new ARMConstant(0));
      ARMCmp cmp = new ARMCmp(leftReg, rightReg);
      ARMMovgt movgt = new ARMMovgt(mov.target, new ARMConstant(1));
      
      node.add(mov).add(cmp).add(movgt);
   }
}
