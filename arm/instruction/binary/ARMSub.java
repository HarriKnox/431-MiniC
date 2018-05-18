package arm.instruction.binary;


public class ARMSub extends ARMBinary
{
   public ARMSub(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMSub(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
