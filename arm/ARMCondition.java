package arm;


public enum ARMCondition
{
   NONE,
   LT, LE,
   EQ, NE,
   GE, GT;
   
   public String toString()
   {
      if (this == ARMCondition.NONE)
         return "";
      
      return this.name();
   }
}
