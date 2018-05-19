package arm.value.integer;


public class ARMGlobal extends ARMImmediate
{
   public final String name;
   
   
   public ARMGlobal(name)
   {
      this.name = name;
   }
   
   
   public static final ARMGlobal PRINT_FORMAT
         = new ARMGlobal(".PRINT_FORMAT");
   
   public static final ARMGlobal PRINTLN_FORMAT
         = new ARMGlobal(".PRINTLN_FORMAT");
   
   public static final ARMGlobal SCANF_FORMAT
         = new ARMGlobal(".SCANF_FORMAT");
}
