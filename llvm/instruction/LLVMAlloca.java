package llvm.instruction;


import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import llvm.value.variable.LLVMVariable;

import arm.ARMCFGNode;


public class LLVMAlloca extends LLVMInstruction
{
   public final LLVMVariable pointer;
   
   
   public LLVMAlloca(LLVMVariable pointer)
   {
      this.pointer = pointer;
   }
   
   
   @Override
   public String llvmString()
   {
      return this.pointer.llvmString() + " = alloca "
            + this.pointer.type.llvmString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      ; /* alloca translates to a no-op */
   }
}
