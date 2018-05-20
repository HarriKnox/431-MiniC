package arm.value.immediate;


public class ARMGlobal extends ARMImmediate
{
   public final String name;
   
   
   public ARMGlobal(String name)
   {
      this.name = name;
   }
   
   
   @Override
   public String lowerARMString()
   {
      return "#:lower16:" + this.name;
   }
   
   
   @Override
   public String upperARMString()
   {
      return "#:upper16:" + this.name;
   }
   
   
   public static final ARMGlobal PRINT_FORMAT
         = new ARMGlobal(".PRINT_FORMAT");
   
   public static final ARMGlobal PRINTLN_FORMAT
         = new ARMGlobal(".PRINTLN_FORMAT");
   
   public static final ARMGlobal SCANF_FORMAT
         = new ARMGlobal(".SCANF_FORMAT");
}
