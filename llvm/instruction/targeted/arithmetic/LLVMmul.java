package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;


public class LLVMmul extends LLVMArithmetic
{
   public LLVMmul(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "mul";
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMMul(
            this.target.buildARM(node),
            this.left.buildARM(node),
            this.right.buildARM(node)));
   }
}
