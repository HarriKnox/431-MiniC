package arm.instruction.binary;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMRsb extends ARMBinary
{
   public ARMRsb(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMRsb(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
