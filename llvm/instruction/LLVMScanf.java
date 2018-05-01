package llvm.instruction;


import llvm.value.LLVMRegister;
import llvm.value.LLVMReadScratch;


public class LLVMScanf extends LLVMInstruction
{
   public final LLVMRegister target;
   
   
   public LLVMScanf()
   {
      this.target = new LLVMReadScratch();
   }
}
