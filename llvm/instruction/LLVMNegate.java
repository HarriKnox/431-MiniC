package llvm.instruction;


import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public class LLVMNegate extends LLVMInstruction
{
   public final LLVMValue source;
   
   
   public LLVMNegate(LLVMValue source)
   {
      super(new LLVMRegister(source.type));
      
      this.source = source;
   }
}
