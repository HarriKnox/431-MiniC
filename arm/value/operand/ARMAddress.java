package arm.value.operand;


public class ARMAddress extends ARMOperand
{
   public final ARMRegister source;
   public final int offset;
   
   
   public ARMAddress(ARMRegister source, int offset)
   {
      this.source = source;
      this.offset = offset;
   }
   
   
   public ARMAddress(ARMRegister source)
   {
      this(source, 0);
   }
   
   
   @Override
   public String armString()
   {
      return this.source.armString() + ", #" + Integer.toString(this.offset);
   }
   
   
   @Override
   public ARMRegister getRegister()
   {
      return this.source;
   }
   
   
   @Override
   public boolean isValid()
   {
      return this.source.isValid();
   }
}
