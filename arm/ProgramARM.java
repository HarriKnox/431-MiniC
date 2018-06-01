package arm;


import java.io.PrintWriter;

import java.util.Arrays;

import arm.declaration.ARMFunctions;
import arm.declaration.ARMGlobals;

import common.Options;


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
   
   
   public void writeARM(Options opts, PrintWriter printer)
   {
      printer.println(".arch armv7-a");
      printer.println(".text");
      
      this.globals.writeARM(printer);
      
      printer.println(".global __aeabi_idiv");
      printer.println();
      
      printer.println();
      this.functions.writeARM(printer);
      
      
      printer.println();
      printer.println(".section .rodata");
      printer.println();
      
      printer.println(".align 2");
      printer.print(PRINTLN_FORMAT.name);
      printer.println(':');
      printer.println("   .asciz \"%d\\n\"");
      printer.println();
      
      printer.println(".align 2");
      printer.print(PRINT_FORMAT.name);
      printer.println(':');
      printer.println("   .asciz \"%d \"");
      printer.println();
      
      printer.println(".align 2");
      printer.print(SCANF_FORMAT.name);
      printer.println(':');
      printer.println("   .asciz \"%d\"");
      
      
      this.functions.allocate();
   }
}
