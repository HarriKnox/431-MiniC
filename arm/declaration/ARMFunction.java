package arm.declaration;


import java.io.PrintWriter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import arm.ARMCFGNode;
import arm.ARMInterferenceEdge;

import arm.value.operand.ARMAddress;


public class ARMFunction
{
   public final String name;
   public final List<ARMCFGNode> nodes;
   public final int localCount;
   public final ARMAddress returnValue;
   
   
   public ARMFunction(String name, List<ARMCFGNode> nodes, int localCount,
         ARMAddress returnValue)
   {
      this.name = name;
      this.nodes = nodes;
      this.localCount = localCount;
      this.returnValue = returnValue;
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
      
      
      /* Load return value into r0 */
      if (this.returnValue != null)
      {
         printer.print("   ldr r0, [");
         printer.print(this.returnValue.armString());
         printer.println(']');
      }
      
      
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
   
   
   public void allocateRegisters()
   {
      /* It's more efficient to run the LVA through the nodes backwards */
      List<ARMCFGNode> revNodes = this.reverseNodeList();
      
      
      /* Generate Phase */
      for (ARMCFGNode node : revNodes)
         node.makeGenKillSets();
      
      
      /* Propagation Phase */
      boolean changed;
      
      do
      {
         changed = false;
         
         for (ARMCFGNode node : revNodes)
            changed |= node.updateLiveSet();
      }
      while (changed);
      
      
      /* Interference Phase */
      Set<ARMInterferenceEdge> interferenceGraph = new LinkedHashSet<>();
      
      for (ARMCFGNode node : revNodes)
         for (ARMInterferenceEdge interference : node.getInterferences())
            interferenceGraph.add(interference);
      
      
      for (ARMInterferenceEdge edge : interferenceGraph)
      {
         System.out.print(edge.left.armString());
         System.out.print(", ");
         System.out.println(edge.right.armString());
      }
   }
   
   
   private List<ARMCFGNode> reverseNodeList()
   {
      List<ARMCFGNode> newNodes = new LinkedList<>();
      
      
      for (ARMCFGNode node : this.nodes)
         newNodes.add(0, node);
      
      
      return newNodes;
   }
}
