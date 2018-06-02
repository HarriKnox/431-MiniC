package arm.declaration;


import java.io.PrintWriter;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import arm.ARMCFGNode;
import arm.ARMInterferenceEdge;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static arm.value.operand.ARMRegister.R0;
import static arm.value.operand.ARMRegister.R1;
import static arm.value.operand.ARMRegister.R2;
import static arm.value.operand.ARMRegister.R3;
import static arm.value.operand.ARMRegister.FP;
import static arm.value.operand.ARMRegister.IP;
import static arm.value.operand.ARMRegister.SP;
import static arm.value.operand.ARMRegister.LR;
import static arm.value.operand.ARMRegister.PC;


public class ARMFunction
{
   public final String name;
   public final List<ARMCFGNode> nodes;
   public final int localCount;
   public final int paramCount;
   public final ARMAddress returnValue;
   
   private int highestRegisterUsed = 0;
   
   
   public ARMFunction(String name, List<ARMCFGNode> nodes, int localCount,
         int paramCount, ARMAddress returnValue)
   {
      this.name = name;
      this.nodes = nodes;
      this.localCount = localCount;
      this.paramCount = paramCount;
      this.returnValue = returnValue;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      int stackSize = ((this.highestRegisterUsed > 10)
            ? (this.localCount + this.highestRegisterUsed - 8)
            : this.localCount) * 4;
      
      printer.println(".align 2");
      
      printer.print(".global ");
      printer.println(this.name);
      
      printer.print(this.name);
      printer.println(':');
      
      
      /* Setting up frame */
      printer.println("   push {fp, lr}");
      printer.println("   add fp, sp, #4");
      
      
      printExtraPushPop(printer, this.highestRegisterUsed, true);
      
      
      /* Allocate stack for locals (if needed) */
      if (this.localCount > 0)
      {
         printer.print("   sub sp, #");
         printer.println(stackSize);
      }
      
      
      /* Move parameters to stack-pointer-relative addresses */
      for (int i = 0; i < paramCount && i < 4; i++)
      {
         printer.print("   str r");
         printer.print(i);
         printer.print(", [fp, #-");
         printer.print((i * 4) + 8);
         printer.println(']');
      }
      
      for (int i = 4; i < paramCount; i++)
      {
         printer.print("   ldr r0, [fp, #");
         printer.print((i - 3) * 4);
         printer.println(']');
         
         printer.print("   str r0, [fp, #-");
         printer.print((i * 4) + 8);
         printer.println(']');
      }
      
      
      /* Write all node instructions */
      for (ARMCFGNode node : this.nodes)
         node.writeARM(printer,
               this.highestRegisterUsed > 10,
               this.localCount);
      
      
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
         printer.println(stackSize);
      }
      
      
      printExtraPushPop(printer, this.highestRegisterUsed, false);
      
      
      /* ARM return */
      printer.println("   pop {fp, pc}");
      
      printer.print(".size ");
      printer.print(this.name);
      printer.print(", .-");
      printer.println(this.name);
      
      printer.println();
   }
   
   
   private static void printExtraPushPop(
         PrintWriter printer,
         int highest, boolean isPush)
   {
      if (highest < 4)
         return;
      
      if (highest > 10)
         highest = 10;
      
      printer.print("   ");
      printer.print(isPush ? "push" : "pop");
      printer.print(" {r4");
      
      
      for (int i = 5; i <= highest; i++)
      {
         printer.print(", r");
         printer.print(i);
      }
      
      printer.println('}');
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
      Map<ARMRegister, Set<ARMRegister>> interferences = new LinkedHashMap<>();
      
      for (ARMCFGNode node : revNodes)
      {
         for (ARMInterferenceEdge interf : node.getInterferences())
         {
            addInterference(interf.left , interf.right, interferences);
            addInterference(interf.right, interf.left , interferences);
         }
      }
      
      
      /* Graph Coloring Phase */
      interferences.remove(R0);
      interferences.remove(R1);
      interferences.remove(R2);
      interferences.remove(R3);
      interferences.remove(IP);
      
      
      Deque<Map.Entry<ARMRegister, Set<ARMRegister>>> stack
            = new LinkedList<>();
      
      while (!interferences.isEmpty())
         stack.push(getRegister(interferences));
      
      
      while (!stack.isEmpty())
      {
         Map.Entry<ARMRegister, Set<ARMRegister>> top = stack.pop();
         
         int number = getLowestNumber(top.getValue());
         
         if (number > this.highestRegisterUsed)
            this.highestRegisterUsed = number;
         
         top.getKey().setNumber(number);
      }
   }
   
   
   private static int getLowestNumber(Set<ARMRegister> regs)
   {
      for (int i = 0; ; i++)
      {
         boolean ok = true;
         
         for (ARMRegister reg : regs)
         {
            if (reg.getNumber() == i)
            {
               ok = false;
               break;
            }
         }
         
         if (ok)
            return i;
      }
   }
   
   
   private static Map.Entry<ARMRegister, Set<ARMRegister>> getRegister(
         Map<ARMRegister, Set<ARMRegister>> interferences)
   {
      Map.Entry<ARMRegister, Set<ARMRegister>> minEntry
            = interferences.entrySet().iterator().next();
      
      int minSize = minEntry.getValue().size();
      
      
      /* Get register with fewest interferences */
      for (Map.Entry<ARMRegister, Set<ARMRegister>> entry
            : interferences.entrySet())
      {
         int entrySize = entry.getValue().size();
         
         if (entrySize < minSize)
         {
            minEntry = entry;
            minSize = entrySize;
         }
      }
      
      
      /* Remove key from interference graph */
      ARMRegister key = minEntry.getKey();
      
      interferences.remove(key);
      
      for (ARMRegister other : minEntry.getValue())
         if (other != R0 && other != R1 && other != R2 && other != R3 && other != IP)
            interferences.get(other).remove(key);
      
      
      return minEntry;
   }
   
   
   private static void addInterference(ARMRegister left, ARMRegister right,
         Map<ARMRegister, Set<ARMRegister>> interferences)
   {
      /* Return if the left register is a special-purpose register */
      if (left == FP || left == IP || left == SP || left == LR || left == PC)
         return;
      
      
      /* If left is OK, even if right is not, add entry */
      if (!interferences.containsKey(left))
         interferences.put(left, new LinkedHashSet<>());
      
      
      /* Return if the right register is a special-purpose register */
      if (right == FP || right == IP || right == SP
            || right == LR || right == PC)
         return;
      
      
      interferences.get(left).add(right);
   }
   
   
   private List<ARMCFGNode> reverseNodeList()
   {
      List<ARMCFGNode> newNodes = new LinkedList<>();
      
      
      for (ARMCFGNode node : this.nodes)
         newNodes.add(0, node);
      
      
      return newNodes;
   }
}
