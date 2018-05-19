package arm.operand.integer;


public class ARMInt extends ARMImmediate
{
   public final int value;
   
   
   public ARMInt(int value)
   {
      this.value = value;
   }
   
   
   @Override
   public String lowerARMString()
   {
      return String.format("#0x%04x", this.value & 0xffff);
   }
   
   
   @Override
   public String upperARMString()
   {
      return String.format("#0x%04x", (this.value >> 16) & 0xffff);
   }
}
