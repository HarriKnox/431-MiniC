package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovne extends ARMMov
{
   public ARMMovne(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovne(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "ne";
   }
}
