package arm.link;


import java.util.List;

import arm.ARMCFGNode;

import common.Printer;


import static java.util.Collections.singletonList;


public class ARMJump extends ARMLink
{
   public final ARMCFGNode target;
   public final boolean loop;
   
   
   public ARMJump(ARMCFGNode target, boolean loop)
   {
      this.target = target;
      this.loop = loop;
   }
   
   
   @Override
   public void writeARM(Printer printr, boolean spilled, int localCount)
   {
      printr.print("   b ").println(this.target.armString());
   }
   
   
   @Override
   public List<ARMCFGNode> getSuccessors()
   {
      return singletonList(this.target);
   }
}
