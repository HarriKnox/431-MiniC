package llvm.instruction;


public abstract class LLVMInstruction
{
   public final LLVMVariable target;
   
   
   public LLVMInstruction(LLVMVariable target)
   {
      this.target = target;
   }
}
