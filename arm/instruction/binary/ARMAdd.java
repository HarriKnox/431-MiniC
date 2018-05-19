package arm.instruction.binary;


public class ARMAdd extends ARMBinary
{
   public ARMAdd(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMAdd(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
}
