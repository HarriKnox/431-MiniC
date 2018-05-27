package arm.link;


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
   
   
}
