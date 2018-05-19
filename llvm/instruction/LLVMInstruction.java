package llvm.instruction;


import arm.ARMCFGNode;


public abstract class LLVMInstruction
{
   public abstract String llvmString();
   
   public abstract void buildARM(ARMCFGNode armNode);
}
