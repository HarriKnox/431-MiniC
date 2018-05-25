package arm.instruction;


import arm.value.operand.ARMRegister;


public class ARMMul extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMRegister left;
   public final ARMRegister right;
   
   
   public ARMMul(ARMRegister left, ARMRegister right)
   {
      this(new ARMRegister(), left, right);
   }
   
   public ARMMul(ARMRegister target, ARMRegister left, ARMRegister right)
   {
      this.target = target;
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   public String armString()
   {
      return "mul " + this.target.armString() + ", "
            + this.left.armString() + ", " + this.right.armString();
   }
}
