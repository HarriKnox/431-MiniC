package arm.instruction.mov;


import arm.instruction.ARMInstruction;


public class ARMMov extends ARMInstruction
{
   public final ARMConditions condition;
   public final ARMRegister target;
   public final ARMValue value;
   
   
   public ARMMov(ARMValue value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMov(ARMRegister target, ARMValue value)
   {
      this(ARMConditions.NONE, target, value);
   }
   
   
   public ARMMov(ARMConditions condition, ARMRegister target, ARMValue value)
   {
      this.condition = condition;
      this.target = target;
      this.value = value;
   }
}
