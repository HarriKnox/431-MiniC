package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMslt extends LLVMComparison
{
   public LLVMslt(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "slt";
   }
   
   /*
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMov mov = new ARMMov(new ARMInt(0));
      ARMCmp cmp = new ARMCmp(leftReg, rightReg);
      ARMMovlt movlt = new ARMMovlt(mov.target, new ARMInt(1));
      
      node.add(mov).add(cmp).add(movlt);
      
      return mov.target;
   }*/
}
