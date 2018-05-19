package arm.instruction.binary;


public class ARMAnd extends ARMBinary
{
   public ARMAnd(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMAnd(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
