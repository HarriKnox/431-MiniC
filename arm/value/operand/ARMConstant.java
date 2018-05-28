package arm.value.operand;


public class ARMConstant extends ARMOperand
{
   public final int number;
   
   
   public ARMConstant(int number)
   {
      this.number = number;
   }
   
   
   @Override
   public String armString()
   {
      return '#' + Integer.toString(this.number);
   }
   
   
   @Override
   public ARMRegister getRegister()
   {
      return null;
   }
}
