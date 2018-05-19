package llvm.instruction;


public abstract class LLVMInstruction
{
   public abstract String llvmString();
   
   public abstract void buildARM(ARMCFGNode armNode);
}
