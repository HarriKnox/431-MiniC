package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovgt extends ARMMov
{
   public ARMMovgt(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMovgt(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "gt";
   }
}
