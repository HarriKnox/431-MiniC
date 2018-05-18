package arm.instruction.binary;


public class ARMAdd extends ARMBinary
{
   public ARMAdd(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      super(target, left, right);
   }
   
   
   public ARMAdd(ARMRegister left, ARMOperandTwo right)
   {
      super(left, right);
   }
}
