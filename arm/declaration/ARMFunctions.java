package arm.declaration;


import java.util.List;

import common.Printer;


public class ARMFunctions
{
   private List<ARMFunction> functions;
   
   
   public ARMFunctions(List<ARMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeARM(Printer printr)
   {
      for (ARMFunction function : this.functions)
         function.writeARM(printr);
   }
}
