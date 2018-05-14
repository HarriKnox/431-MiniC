package arm.instruction;


public class ARMRsb extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMConstant right;
   
   public ARMRsb(ARMRegister left, ARMConstant right)
   {
      this.left = left;
      this.right = right;
   }
}
