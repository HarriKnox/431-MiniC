package arm.declaration;


import java.io.PrintWriter;

import java.util.List;

import arm.ARMCFGNode;


public class ARMFunction
{
   public final String name;
   public final List<ARMCFGNode> nodes;
   public final int localCount;
   
   
   public ARMFunction(String name, List<ARMCFGNode> nodes, int localCount)
   {
      this.name = name;
      this.nodes = nodes;
      this.localCount = localCount;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      printer.println(".align 2");
      
      printer.print(".global ");
      printer.println(this.name);
      
      printer.print(this.name);
      printer.println(':');
      
      
      /* Setting up frame */
      printer.println("   push {fp, lr}");
      printer.println("   add fp, sp, #4");
      
      
      /* Allocate stack for locals (if needed) */
      if (this.localCount > 0)
      {
         printer.print("   sub sp, #");
         printer.println(this.localCount * 4);
      }
      
      
      /* Write all node instructions */
      for (ARMCFGNode node : this.nodes)
         node.writeARM(printer);
      
      
      /* Pop off all of stack at once */
      if (this.localCount > 0)
      {
         printer.print("   add sp, #");
         printer.println(this.localCount * 4);
      }
      
      
      /* ARM return */
      printer.println("   pop {fp, pc}");
      
      printer.print(".size ");
      printer.print(this.name);
      printer.print(", .-");
      printer.println(this.name);
      
      printer.println();
   }
}
