package arm.instruction;


public class ARMSub extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMRegister right;
   
   public ARMSub(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
}
