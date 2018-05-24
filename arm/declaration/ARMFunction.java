package arm.declaration;


import java.io.PrintWriter;

import java.util.List;

import arm.ARMCFGNode;


public class ARMFunction
{
   public final String name;
   public final List<ARMCFGNode> nodes;
   
   
   public ARMFunction(String name, List<ARMCFGNode> nodes)
   {
      this.name = name;
      this.nodes = nodes;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      printer.println(".align 2");
      
      printer.print(".global ");
      printer.println(this.name);
      
      printer.print(this.name);
      printer.println(':');
      
      
      for (ARMCFGNode node : this.nodes)
         node.writeARM(printer);
      
      
      printer.print(".size ");
      printer.print(this.name);
      printer.print(", .-");
      printer.println(this.name);
      
      printer.println();
   }
}
