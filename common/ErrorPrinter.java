package common;


import org.antlr.v4.runtime.Token;


public class ErrorPrinter
{
   private static int errors = 0;
   
   
   public static void printOut(String message)
   {
      System.err.println(message);
      
      errors++;
   }
   
   
   public static void printLine(Token token, String message)
   {
      printOut(tokenPosition(token) + ' ' + message);
   }
   
   
   public static void duplicate(Token token, String type, String thing)
   {
      printLine(token, type + ' ' + thing + " already declared");
   }
   
   
   public static void undeclared(Token token, String type, String thing)
   {
      printLine(token, type + ' ' + thing + " not declared");
   }
   
   
   public static void noField(Token token, String struct, String field)
   {
      printLine(token, "struct " + struct + " does not have field " + field);
   }
   
   public static void badIndex(Token token, String got)
   {
      printLine(token, "attempt to index a(n) " + got);
   }
   
   
   public static void unknownStruct(Token token, String struct)
   {
      printLine(token, "unknown type struct " + struct);
   }
   
   
   public static void wrongArity(Token token,
         String name, int expected, int got)
   {
      printLine(token, "function " + name + " expects "
            + expected + " arguments but got " + got);
   }
   
   
   public static void unexpectedType(Token token, String expected,
         String thing, String got)
   {
      printLine(token, "expected " + expected
            + " value for " + thing + " but got " + got);
   }
   
   
   public static void binaryMistype(Token token,
         String operation, String left, String right)
   {
      printLine(token, "attempt to perform " + operation
            + " on " + left + " and " + right);
   }
   
   
   public static void unaryMistype(Token token, String operation, String got)
   {
      printLine(token, "attempt to perform " + operation + " on " + got);
   }
   
   
   public static void assignMistype(Token token, String left, String right)
   {
      printLine(token, "cannot assign value of " + right + " to " + left);
   }
   
   
   public static void nonReturn(Token token, String funcName)
   {
      printLine(token, "function " + funcName
            + " does not return on all paths");
   }
   
   
   public static void badGuard(Token token, String got)
   {
      printLine(token, "guard cannot be type " + got + ", must be bool");
   }
   
   
   public static void badPrint(Token token, String got)
   {
      printLine(token, "cannot print value of type " + got + ", must be int");
   }
   
   
   public static void badDelete(Token token, String got)
   {
      printLine(token, "cannot delete value of type "
            + got + ", must be struct");
   }
   
   
   public static void badReturn(Token token, String expected, String got)
   {
      printLine(token, "cannot return " + got + ", expected " + expected);
   }
   
   
   public static void codeAfterReturn(Token token)
   {
      printLine(token, "cannot have code after a return");
   }
   
   
   public static void IDK(String here, String what)
   {
      printOut("I have no idea what went wrong in " + here + ": " + what);
   }
   
   
   private static String tokenPosition(Token token)
   {
      return "line " + token.getLine() + ':' + token.getCharPositionInLine();
   }
   
   
   public static int getErrorCount()
   {
      return errors;
   }
}
