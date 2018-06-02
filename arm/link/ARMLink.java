package arm.link;


import java.util.List;

import arm.ARMCFGNode;

import common.Printer;


public abstract class ARMLink
{
   public abstract void writeARM(Printer printr,
         boolean spilled, int localCount);
   
   public abstract List<ARMCFGNode> getSuccessors();
}
