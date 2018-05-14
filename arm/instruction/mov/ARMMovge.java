package arm.instruction.mov;


public class ARMMovge extends ARMMov
{
   public ARMMovge(ARMValue value)
   {
      super(new ARMRegister(), value);
   }
   
   
   public ARMMovge(ARMRegister target, ARMValue value)
   {
      super(target, value)
   }
   
   
   @Override
   protected String conditionString()
   {
      return "ge";
   }
}
