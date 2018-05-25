package llvm.link;


import arm.ARMCFGNode;


public abstract class LLVMLink
{
   public abstract String llvmString();
   
   
   public abstract void buildARM(ARMCFGNode node);
}
