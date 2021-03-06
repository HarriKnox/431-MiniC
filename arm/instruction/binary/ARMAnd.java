package arm.instruction.binary;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMAnd extends ARMBinary
{
   public ARMAnd(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMAnd(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "and";
   }
}
