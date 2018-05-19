package llvm.instruction.targeted.logical;


import llvm.value.operand.LLVMOperand;


public class LLVMand extends LLVMLogical
{
   public LLVMand(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "and";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMAnd(
            this.target.buildARM(node),
            this.left.buildARM(node),
            this.right.buildARM(node)));
   }
}
