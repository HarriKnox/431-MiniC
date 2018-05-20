package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMovge extends ARMMov
{
   public ARMMovge(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMovge(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "ge";
   }
}
