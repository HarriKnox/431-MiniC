package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovne extends ARMMov
{
   public ARMMovne(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMovne(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "ne";
   }
}
