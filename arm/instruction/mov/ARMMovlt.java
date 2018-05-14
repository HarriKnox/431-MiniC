package arm.instruction.mov;


public class ARMMovlt extends ARMMov
{
   public ARMMovlt(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovle(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "lt";
   }
}
