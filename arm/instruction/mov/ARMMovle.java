package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovle extends ARMMov
{
   public ARMMovle(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMovle(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "le";
   }
}
