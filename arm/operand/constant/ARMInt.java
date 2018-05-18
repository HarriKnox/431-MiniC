package arm.operand.constant;


public class ARMInt extends ARMConstant
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
