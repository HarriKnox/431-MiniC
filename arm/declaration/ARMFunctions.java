package arm.declaration;


import java.io.PrintWriter;

import java.util.List;


public class ARMFunctions
{
   private List<ARMFunction> functions;
   
   
   public ARMFunctions(List<ARMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      for (ARMFunction function : this.functions)
         function.writeARM(printer);
   }
   
   
   public void allocate()
   {
      for (ARMFunction func : this.functions)
      {
         func.allocateRegisters();
      }
   }
}
