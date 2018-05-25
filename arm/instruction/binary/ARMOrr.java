package arm.instruction.binary;


import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMOrr extends ARMBinary
{
   public ARMOrr(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      super(target, left, right);
   }
   
   
   public ARMOrr(ARMRegister left, ARMOperand right)
   {
      super(left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "orr";
   }
}
