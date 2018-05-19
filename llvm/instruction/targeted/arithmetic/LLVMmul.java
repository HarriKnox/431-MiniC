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
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMMul mul = new ARMMul(leftReg, rightReg);
      
      node.add(mul);
      
      return mul.target;
   }
}
