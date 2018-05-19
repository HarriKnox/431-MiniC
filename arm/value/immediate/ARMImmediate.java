package arm.value.integer;


import arm.value.ARMValue;


public abstract class ARMImmediate extends ARMValue
{
   public abstract String lowerARMString();
   
   public abstract String upperARMString();
   
   
   @Override
   public String armString()
   {
      return this.lowerARMString();
   }
}
