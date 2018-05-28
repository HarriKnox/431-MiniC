package arm.link;


import java.io.PrintWriter;

import java.util.List;

import arm.ARMCFGNode;


public abstract class ARMLink
{
   public abstract void writeARM(PrintWriter printer);
   
   public abstract List<ARMCFGNode> getSuccessors();
}
