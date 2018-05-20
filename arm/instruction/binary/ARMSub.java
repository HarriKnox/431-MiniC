package arm.instruction.binary;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMSub extends ARMBinary
{
   public ARMSub(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMSub(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
