package arm.value.constant;


import arm.value.ARMValue;


public abstract class ARMConstant extends ARMValue
{
   public abstract String lowerARMString();
   
   public abstract String upperARMString();
}
