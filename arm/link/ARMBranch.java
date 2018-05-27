package arm.link;


import java.io.PrintWriter;

import arm.ARMCFGNode;

import arm.instruction.ARMCmp;

import arm.value.operand.ARMConstant;
import arm.value.operand.ARMRegister;


public class ARMBranch extends ARMLink
{
   public final ARMRegister guard;
   public final boolean loop;
   public final ARMCFGNode thenNode;
   public final ARMCFGNode elseNode;
   
   
   public ARMBranch(ARMRegister guard, boolean loop,
         ARMCFGNode thenNode, ARMCFGNode elseNode)
   {
      this.guard = guard;
      this.loop = loop;
      this.thenNode = thenNode;
      this.elseNode = elseNode;
   }
   
   
   @Override
   public void writeARM(PrintWriter printer)
   {
      printer.print("   ");
      printer.println(new ARMCmp(this.guard, new ARMConstant(1)).armString());
      
      printer.print("   beq ");
      printer.println(this.thenNode.armString());
      
      printer.print("   b ");
      printer.println(this.elseNode.armString());
   }
}
