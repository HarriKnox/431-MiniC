package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovle extends ARMMov
{
   public ARMMovle(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovle(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "le";
   }
}
