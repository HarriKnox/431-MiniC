package arm.link;


import java.io.PrintWriter;

import arm.ARMCFGNode;


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
   public void writeARM(PrintWriter printer)
   {
      printer.print("   b ");
      printer.println(this.target.armString());
   }
}
