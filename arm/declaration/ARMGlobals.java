package arm.declaration;


import java.io.PrintWriter;

import java.util.List;


public class ARMGlobals
{
   private final List<String> globals;
   
   
   public ARMGlobals(List<String> globals)
   {
      this.globals = globals;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      for (String global : this.globals)
         printer.format("   .comm %s, 4, 4%n", global);
      
      printer.println();
   }
}
