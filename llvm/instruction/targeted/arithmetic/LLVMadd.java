package llvm.instruction.targeted.arithmetic;


import llvm.value.operand.LLVMOperand;


public class LLVMadd extends LLVMArithmetic
{
   public LLVMadd(LLVMOperand left, LLVMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "add";
   }
   
   /*
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMAdd add = new ARMAdd(leftReg, rightReg);
      
      node.add(add);
      
      return add.target;
   }*/
}
