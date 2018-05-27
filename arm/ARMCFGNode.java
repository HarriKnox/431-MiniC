package arm;


import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

import arm.instruction.ARMInstruction;

import arm.link.ARMBranch;
import arm.link.ARMJump;
import arm.link.ARMLink;

import arm.value.operand.ARMRegister;


public class ARMCFGNode
{
   public final List<ARMInstruction> instructions = new LinkedList<>();
   public final List<ARMCFGNode> predecessors = new LinkedList<>();
   public ARMCFGNode loopback = null;
   public ARMLink link = null;
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public ARMCFGNode add(ARMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
   
   
   public void jump(ARMCFGNode target)
   {
      target.predecessors.add(this);
      
      this.link = new ARMJump(target, false);
   }
   
   
   public void loopback(ARMCFGNode target)
   {
      target.loopback = this;
      
      this.link = new ARMJump(target, true);
   }
   
   
   public void branch(ARMRegister guard,
         ARMCFGNode thenNode, ARMCFGNode elseNode)
   {
      thenNode.predecessors.add(this);
      elseNode.predecessors.add(this);
      
      this.link = new ARMBranch(guard, false, thenNode, elseNode);
   }
   
   
   public String armString()
   {
      return ".N" + this.getUID();
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      printer.print(this.armString());
      printer.println(':');
      
      
      for (ARMInstruction instruction : this.instructions)
      {
         printer.print("   ");
         printer.println(instruction.armString());
      }
      
      
      /*
      if (this.link != null)
         this.link.writeARM(printer));
      */
   }
}
