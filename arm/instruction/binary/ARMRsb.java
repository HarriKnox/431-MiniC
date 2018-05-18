package arm.instruction.binary;


public class ARMRsb extends ARMBinary
{
   public ARMRsb(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMRsb(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
