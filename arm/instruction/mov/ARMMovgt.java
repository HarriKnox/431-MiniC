package arm.instruction.mov;


public class ARMMovgt extends ARMMov
{
   public ARMMovgt(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovgt(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "gt";
   }
}
