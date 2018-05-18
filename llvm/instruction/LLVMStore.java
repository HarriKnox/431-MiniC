package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.value.operand.LLVMOperand;
import llvm.value.variable.LLVMVariable;


public class LLVMStore extends LLVMInstruction
{
   public final LLVMVariable target;
   public final LLVMOperand source;
   
   
   public LLVMStore(LLVMVariable target, LLVMOperand source)
   {
      this.target = target;
      
      this.source = source;
   }
   
   
   @Override
   public String llvmString()
   {
      return "store " + this.source.llvmTypedString() + ", "
            + this.target.type.llvmString() + "* " + this.target.llvmString();
   }
}
