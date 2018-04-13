package common;

public class ErrorPrinter
{
   private static int errors = 0;
   
   
   private static void printLine(int line, String message, boolean error)
   {
      System.err.println("line " + line + " " + message);
      
      if (!error)
         errors++;
   }
   
   
   public static void duplicate(int line, String thing)
   {
      printLine(line, thing + " already declared", true);
   }
   
   
   public static void unknownStruct(int line, String struct)
   {
      printLine(line, "unknown type struct " + struct + ""
   }
   
   
   public static int getErrorCount()
   {
      return errors;
   }
}
