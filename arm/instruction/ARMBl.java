package arm.instruction;


public class ARMBl extends ARMInstruction
{
   public final String name;
   
   
   public ARMBl(String name)
   {
      this.name = name;
   }
   
   
   @Override
   public String armString()
   {
      return "bl " + this.name;
   }
}
