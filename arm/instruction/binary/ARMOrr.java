package arm.instruction.binary;


public class ARMOrr extends ARMBinary
{
   public ARMOrr(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMOrr(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
