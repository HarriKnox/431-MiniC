package llvm.instruction;


import llvm.value.variable.LLVMVariable;


public abstract class LLVMInstruction
{
   public final LLVMVariable target;
   
   
   public LLVMInstruction(LLVMVariable target)
   {
      this.target = target;
   }
}
