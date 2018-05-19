package arm.value.immediate;


import arm.value.operand.ARMRegister;


public class ARMOffset extends ARMOperand
{
   public final ARMRegister source;
   public final int offset;
   
   
   public ARMOffset(ARMRegister source, int offset)
   {
      this.source = source;
      this.offset = offset;
   }
   
   
   public ARMOffset(ARMRegister source)
   {
      this(source, 0);
   }
   
   
   @Override
   public String armString()
   {
      if (this.offset == 0)
         return this.source.armString();
      
      return this.source.armString() + " #" + Integer.toString(this.offset);
   }
}
