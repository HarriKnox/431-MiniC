package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMoveq extends ARMMov
{
   public ARMMoveq(ARMOperand operand)
   {
      super(new ARMRegister(), operand);
   }
   
   
   public ARMMoveq(ARMRegister target, ARMOperand operand)
   {
      super(target, operand);
   }
   
   
   @Override
   protected String conditionString()
   {
      return "eq";
   }
}
