package arm.instruction;


public class ARMAdd extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMRegister right;
   
   public ARMAdd(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
}
