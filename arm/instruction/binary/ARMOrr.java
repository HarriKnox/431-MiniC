package arm.instruction.binary;


public class ARMOr extends ARMBinary
{
   public ARMOr(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMOr(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
