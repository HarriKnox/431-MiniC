package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovlt extends ARMMov
{
   public ARMMovlt(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMovlt(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "lt";
   }
}
