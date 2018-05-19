package arm.instruction.binary;


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
