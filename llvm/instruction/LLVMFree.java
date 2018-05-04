package llvm.instruction;


import llvm.value.variable.LLVMVariable;


public class LLVMFree extends LLVMInstruction
{
   public final LLVMVariable pointer;
   
   
   public LLVMFree(LLVMVariable pointer)
   {
      this.pointer = pointer;
   }
}
