package arm.instruction.mov;


public class ARMMovne extends ARMMov
{
   public ARMMovne(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovne(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "ne";
   }
}
