package arm.value.integer;


import arm.value.ARMValue;


public abstract class ARMInteger extends ARMValue
{
   public abstract String lowerARMString();
   
   public abstract String upperARMString();
   
   
   @Override
   public String armString()
   {
      return this.lowerARMString();
   }
}
