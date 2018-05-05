package llvm.instruction.targeted;


import java.util.List;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMCall extends LLVMTargetedInstruction
{
   public final String name;
   public final List<LLVMValue> arguments;
   
   
   public LLVMCall(String name, LLVMType type, List<LLVMValue> arguments)
   {
      super(type);
      this.name = name;
      this.arguments = arguments;
   }
}
