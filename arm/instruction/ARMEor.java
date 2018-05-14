package arm.instruction;


public class ARMEor extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMConstant right;
   
   
   public ARMEor(ARMRegister left, ARMConstant right)
   {
      this.left = left;
      this.right = right;
   }
}
