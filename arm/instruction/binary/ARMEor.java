package arm.instruction.binary;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMEor extends ARMBinary
{
   public ARMEor(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMEor(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
