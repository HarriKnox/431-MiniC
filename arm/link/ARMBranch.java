package arm.link;


import arm.ARMCFGNode;

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
   
   
}
