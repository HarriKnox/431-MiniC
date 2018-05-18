package arm.instruction.binary;


import arm.instruction.ARMInstruction;


public abstract class ARMBinary extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMRegister left;
   public final ARMOperandTwo right;
   
   
   public ARMBinary(ARMRegister target, ARMRegister left, ARMOperandTwo right)
   {
      this.target = target;
      this.left = left;
      this.right = right;
   }
   
   
   public ARMBinary(ARMRegister left, ARMOperandTwo right)
   {
      this.left = left;
      this.right = right;
      
      this.target = new ARMRegister();
   }
}
