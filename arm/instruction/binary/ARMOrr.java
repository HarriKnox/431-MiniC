package arm.instruction.binary;


public class ARMOr extends ARMBinary
{
   public ARMOr(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMOr(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
