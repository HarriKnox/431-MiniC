package llvm.instruction.targeted;


import java.util.Iterator;
import java.util.List;

import llvm.instruction.LLVMCallVoid;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMCall extends LLVMTargetedInstruction
{
   private final LLVMCallVoid call;
   
   
   public LLVMCall(String name, LLVMType type, List<LLVMValue> arguments)
   {
      super(type);
      this.call = new LLVMCallVoid(name, type, arguments);
   }
   
   
   @Override
   protected String getInstruction()
   {
      return this.call.llvmString();
   }
}
