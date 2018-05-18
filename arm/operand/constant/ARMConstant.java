package arm.operand.constant;


import arm.operand.ARMOperand;


public abstract class ARMConstant extends ARMOperand
{
   public abstract String lowerARMString();
   
   public abstract String upperARMString();
   
   
   @Override
   public String armString()
   {
      return this.lowerARMString();
   }
}
