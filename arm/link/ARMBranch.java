package arm.link;


import java.util.List;

import arm.ARMCFGNode;

import arm.instruction.ARMCmp;

import arm.value.operand.ARMConstant;
import arm.value.operand.ARMRegister;

import common.Printer;


import static java.util.Arrays.asList;


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
   public void writeARM(Printer printr, boolean spilled, int localCount)
   {
      for (String str
            : new ARMCmp(this.guard, new ARMConstant(1))
                  .armStrings(spilled, localCount))
         printr.print("   ").println(str);
      
      printr.print("   beq ")
            .println(this.thenNode.armString())
      
            .print("   b ")
            .println(this.elseNode.armString());
   }
   
   
   @Override
   public List<ARMCFGNode> getSuccessors()
   {
      return asList(new ARMCFGNode[]{this.thenNode, this.elseNode});
   }
}
