package arm.instruction.mov;


import arm.instruction.ARMInstruction;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMov extends ARMInstruction
{
   public final ARMConditions condition;
   public final ARMRegister target;
   public final ARMValue value;
   
   
   public ARMMov(ARMValue value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMov(ARMRegister target, ARMValue value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   protected String conditionString()
   {
      return "";
   }
   
   
   @Override
   public String armString()
   {
      return "mov" + this.conditionString() + " "
            + this.target.armString() + ", " + this.value.armString();
   }
}
