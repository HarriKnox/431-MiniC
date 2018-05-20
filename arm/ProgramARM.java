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
}
