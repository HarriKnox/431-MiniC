package arm.instruction.binary;


public class ARMAnd extends ARMBinary
{
   public ARMAnd(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMAnd(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
