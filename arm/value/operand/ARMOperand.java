package arm.value.operand;


import arm.value.ARMValue;


public abstract class ARMOperand extends ARMValue
{
   public abstract ARMRegister getRegister();
}
