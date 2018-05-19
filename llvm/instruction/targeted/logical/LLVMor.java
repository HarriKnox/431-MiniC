package llvm.instruction.targeted.logical;


import llvm.value.operand.LLVMOperand;


public class LLVMor extends LLVMLogical
{
   public LLVMor(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "or";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMOrr(
            this.target.buildARM(node),
            this.left.buildARM(node),
            this.right.buildARM(node)));
   }
}
