package arm.instruction;


import arm.value.ARMRegister;

import arm.value.constant.ARMConstant;


public class ARMMovt extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMConstant value;
   
   
   public ARMMovt(ARMConstant value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMovt(ARMRegister target, ARMConstant value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   @Override
   public String armString()
   {
      return "movt " + this.target.armString()
            + ", " + this.value.upperARMString();
   }
}
