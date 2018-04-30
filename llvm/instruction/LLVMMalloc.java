package llvm.instruction;


public class LLVMMalloc extends LLVMInstruction
{
   public final LLVMRegister result;
   public final int size;
   
   
   public LLVMMalloc(LLVMRegister result, int numberOfFields)
   {
      this.result = result;
      this.size = numberOfFields * 4;
   }
}
