package arm.link;


import java.io.PrintWriter;

import java.util.List;

import arm.ARMCFGNode;


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
   public void writeARM(PrintWriter printer, boolean spilled, int localCount)
   {
      printer.print("   b ");
      printer.println(this.target.armString());
   }
   
   
   @Override
   public List<ARMCFGNode> getSuccessors()
   {
      return singletonList(this.target);
   }
}
