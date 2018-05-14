package arm.instruction;


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
