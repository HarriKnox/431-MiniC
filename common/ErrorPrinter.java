package common;

public class ErrorPrinter
{
   private static int errors = 0;
   
   
   public static void printOut(String message)
   {
      System.out.println(message);
      
      errors++;
   }
   
   
   public static void printLine(int line, String message)
   {
      printOut("line " + line + " " + message);
   }
   
   
   public static void duplicate(int line, String thing)
   {
      printLine(line, thing + " already declared");
   }
   
   
   public static void undeclared(int line, String thing)
   {
      printLine(line, thing + " not declared");
   }
   
   
   public static void unknownStruct(int line, String struct)
   {
      printLine(line, "unknown type struct " + struct);
   }
   
   
   public static void nonReturn(int line, String funcName)
   {
      printLine(line, "function " + funcName + " does not return on all paths");
   }
   
   
   public static void unexpectedType(int line, String expected, String thing, String got)
   {
      printLine(line, "expected " + expected + " value for " + thing + " but got " + got
   }
   
   
   public static void noField(int line, String struct, String field)
   {
      printLine(line, "struct " + struct + " does not have field " + field);
   }
   
   
   public static void IDK(String where, String what)
   {
      printOut("I have no idea what went wrong in " + where + ": " + what);
   }
   
   
   public static int getErrorCount()
   {
      return errors;
   }
}
