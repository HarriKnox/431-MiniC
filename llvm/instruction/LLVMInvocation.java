package llvm.instruction;


import java.util.List;

import llvm.value.LLVMRegister;
import llvm.value.LLVMValue;


public class LLVMInvocation extends LLVMInstruction
{
   public final LLVMRegister target;
   public final String name;
   public final List<LLVMValue> arguments;
   
   
   public LLVMInvocation(String name, LLVMType type, List<LLVMValue> arguments)
   {
      this.target = new LLVMRegister(type);
      this.name = name;
      this.arguments = arguments;
   }
}
