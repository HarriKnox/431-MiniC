package llvm.instruction.targeted.comparison;


import llvm.value.LLVMValue;


public class LLVMne extends LLVMComparison
{
   public LLVMne(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "ne";
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMov mov = new ARMMov(new ARMInt(0));
      ARMCmp cmp = new ARMCmp(leftReg, rightReg);
      ARMMovne movne = new ARMMovne(mov.target, new ARMInt(1));
      
      node.add(mov).add(cmp).add(movne);
      
      return mov.target;
   }
}
