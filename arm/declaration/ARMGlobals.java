package arm.declaration;


import java.util.List;

import common.Printer;


public class ARMGlobals
{
   private final List<String> globals;
   
   
   public ARMGlobals(List<String> globals)
   {
      this.globals = globals;
   }
   
   
   public void writeARM(Printer printr)
   {
      for (String global : this.globals)
         printr.print("   .comm ").print(global).println(", 4, 4");
      
      printr.println();
   }
}
