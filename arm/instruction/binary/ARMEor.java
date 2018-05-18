package arm.instruction.binary;


public class ARMEor extends ARMBinary
{
   public ARMEor(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMEor(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
