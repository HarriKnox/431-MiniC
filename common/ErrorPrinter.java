package common;


import org.antlr.v4.runtime.Token;


public class ErrorPrinter
{
   private static int errors = 0;
   
   
   public static void printOut(String message)
   {
      System.out.println(message);
      
      errors++;
   }
   
   
   public static void printLine(Token token, String message)
   {
      printOut(tokenPosition(token) + " " + message);
   }
   
   
   public static void duplicate(Token token, String thing)
   {
      printLine(token, thing + " already declared");
   }
   
   
   public static void undeclared(Token token, String thing)
   {
      printLine(token, thing + " not declared");
   }
   
   
   public static void unknownStruct(Token token, String struct)
   {
      printLine(token, "unknown type struct " + struct);
   }
   
   
   public static void nonReturn(Token token, String funcName)
   {
      printLine(token, "function " + funcName + " does not return on all paths");
   }
   
   
   public static void unexpectedType(Token token, String expected, String thing, String got)
   {
      printLine(token, "expected " + expected + " value for " + thing + " but got " + got);
   }
   
   
   public static void noField(Token token, String struct, String field)
   {
      printLine(token, "struct " + struct + " does not have field " + field);
   }
   
   
   public static void IDK(String where, String what)
   {
      printOut("I have no idea what went wrong in " + where + ": " + what);
   }
   
   
   private static String tokenPosition(Token token)
   {
      return "line " + token.getLine() + ":" + token.getCharPositionInLine();
   }
   
   
   public static int getErrorCount()
   {
      return errors;
   }
   
   
   public static class Issue
   {
      public final Token token;
      public final String message;
      
      public Issue(Token token, String message)
      {
         this.token = token;
         this.message = message;
      }
   }
}
