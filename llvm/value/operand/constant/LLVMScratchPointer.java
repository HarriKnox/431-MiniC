package llvm.value.operand.constant;


import llvm.type.LLVMPointerType;

import llvm.value.variable.LLVMGlobal;

import arm.ARMCFGNode;

import arm.instruction.ARMMovt;
import arm.instruction.ARMMovw;

import arm.value.immediate.ARMGlobal;

import arm.value.operand.ARMRegister;


public class LLVMScratchPointer extends LLVMConstant
{
   private LLVMScratchPointer()
   {
      super(new LLVMPointerType(LLVMGlobal.SCANF_SCRATCH.type));
   }
   
   
   @Override
   public String llvmString()
   {
      return LLVMGlobal.SCANF_SCRATCH.llvmString();
   }
   
   
   public static final LLVMScratchPointer SCANF_SCRATCH
         = new LLVMScratchPointer();
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMGlobal(LLVMGlobal.SCANF_SCRATCH.name));
      ARMMovt movt = new ARMMovt(movw.target, movw.value);
      
      node.add(movw).add(movt);
      
      return movw.target;
   }
}
