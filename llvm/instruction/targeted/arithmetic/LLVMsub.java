package llvm.instruction.targeted.arithmetic;


import llvm.value.LLVMValue;


public class LLVMsub extends LLVMArithmetic
{
   public LLVMsub(LLVMValue left, LLVMValue right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "sub";
   }
   
   /*
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMRegister leftReg = this.left.buildARM(node);
      ARMRegister rightReg = this.right.buildARM(node);
      
      ARMSub sub = new ARMSub(leftReg, rightReg);
      
      node.add(sub);
      
      return sub.target;
   }*/
}
