package arm.instruction;


import arm.value.ARMRegister;

import arm.value.constant.ARMConstant;


public class ARMMovw extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMConstant value;
   
   
   public ARMMovw(ARMConstant value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMovw(ARMRegister target, ARMConstant value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   @Override
   public String armString()
   {
      return "movw " + this.target.armString()
            + ", " + this.value.lowerARMString();
   }
}
