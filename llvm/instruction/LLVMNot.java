package llvm.instruction;


import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public class LLVMNot extends LLVMInstruction
{
   public final LLVMValue source;
   
   
   public LLVMNot(LLVMValue source)
   {
      super(new LLVMRegister(source.type));
      
      this.source = source;
   }
}
