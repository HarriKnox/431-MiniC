package llvm.link;


import java.io.PrintWriter;

import arm.ARMCFGNode;


public abstract class LLVMLink
{
   public abstract void writeLLVM(PrintWriter printer);
   
   public abstract void buildARM(ARMCFGNode node);
}
