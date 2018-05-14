package arm.instruction;


public class ARMOr extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMRegister right;
   
   
   public ARMOr(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
}
