package arm.instruction;


import arm.value.operand.ARMRegister;


public class ARMMul extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMRegister right;
   
   public ARMMul(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
}
