package llvm.link;


import arm.ARMCFGNode;

import common.Printer;


public abstract class LLVMLink
{
   public abstract void writeLLVM(Printer printr);
   
   public abstract void buildARM(ARMCFGNode node);
}
