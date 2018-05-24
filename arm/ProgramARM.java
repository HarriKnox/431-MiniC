package arm;


import arm.declaration.ARMFunctions;
import arm.declaration.ARMGlobals;


public class ProgramARM
{
   public final ARMGlobals globals;
   public final ARMFunctions functions;
   
   
   public ProgramARM(ARMGlobals globals, ARMFunctions functions)
   {
      this.globals = globals;
      this.functions = functions;
   }
   
   
   public static void writeARM(Options opts, Printer printer)
   {
      printer.println(".arch armv7-a");
   }
}
