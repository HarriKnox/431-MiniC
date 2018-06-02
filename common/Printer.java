package common;


import java.io.OutputStream;
import java.io.PrintWriter;


public class Printer
{
   private PrintWriter printer;
   
   
   public Printer(PrintWriter printer)
   {
      this.printer = printer;
   }
   
   
   public Printer print(Object o)
   {
      this.printer.print(o);
      
      return this;
   }
   
   
   public Printer println(Object o)
   {
      this.printer.println(o);
      
      return this;
   }
   
   
   public Printer println()
   {
      this.printer.println();
      
      return this;
   }
}
