package arm;


import java.util.Arrays;

import arm.declaration.ARMFunctions;
import arm.declaration.ARMGlobals;

import common.Options;
import common.Printer;


import static arm.value.immediate.ARMGlobal.PRINT_FORMAT;
import static arm.value.immediate.ARMGlobal.PRINTLN_FORMAT;
import static arm.value.immediate.ARMGlobal.SCANF_FORMAT;


public class ProgramARM
{
   public final ARMGlobals globals;
   public final ARMFunctions functions;
   
   
   public ProgramARM(ARMGlobals globals, ARMFunctions functions)
   {
      this.globals = globals;
      this.functions = functions;
   }
   
   
   public void writeARM(Options opts, Printer printr)
   {
      printr.println(".arch armv7-a").println(".text");
      
      this.globals.writeARM(printr);
      
      printr.println(".global __aeabi_idiv").println().println();
      
      
      this.functions.writeARM(printr, opts);
      
      
      printr.println()
            .println(".section .rodata")
            .println()
      
            .println(".align 2")
            .print(PRINTLN_FORMAT.name)
            .println(':')
            .println("   .asciz \"%d\\n\"")
            .println()
      
            .println(".align 2")
            .print(PRINT_FORMAT.name)
            .println(':')
            .println("   .asciz \"%d \"")
            .println()
      
            .println(".align 2")
            .print(SCANF_FORMAT.name)
            .println(':')
            .println("   .asciz \"%d\"");
   }
}
