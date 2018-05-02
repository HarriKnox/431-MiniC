package llvm.instruction;


public abstract class LLVMInstruction
{
   public final LLVMRegister target;
   
   
   public LLVMInstruction(LLVMRegister target)
   {
      this.target = target;
   }
}
