package arm.operand.constant.label;


public class ARMStdio extends ARMLabel
{
   public final String name;
   public final String asciz;
   
   private ARMStdio(String name, String asciz)
   {
      this.name = name;
      this.asciz = asciz;
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
   
   
   public static final ARMStdio PRINT_FORMAT
         = new ARMStdio(".PRINT_FORMAT", "%d\\n");
   
   public static final ARMStdio PRINTLN_FORMAT
         = new ARMStdio(".PRINTLN_FORMAT", "%d ");
   
   public static final ARMStdio SCANF_FORMAT
         = new ARMStdio(".SCANF_FORMAT", "%d");
}
