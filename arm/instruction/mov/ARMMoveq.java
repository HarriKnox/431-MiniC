package arm.instruction.mov;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMMoveq extends ARMMov
{
   public ARMMoveq(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMoveq(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "eq";
   }
}
