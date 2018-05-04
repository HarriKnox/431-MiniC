package llvm.instruction;


import java.util.List;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMCallVoid extends LLVMInstruction
{
   public final String name;
   public final List<LLVMValue> arguments;
   
   
   public LLVMCallVoid(String name, List<LLVMValue> arguments)
   {
      this.name = name;
      this.arguments = arguments;
   }
}
