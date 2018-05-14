package arm.instruction;


public class ARMAnd extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMRegister right;
   
   
   public ARMAnd(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
}
