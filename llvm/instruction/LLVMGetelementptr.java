package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.type.LLVMType;

import llvm.value.operand.register.LLVMRegister;

import llvm.value.variable.LLVMField;

import arm.ARMCFGNode;

import arm.instruction.binary.ARMAdd;

import arm.value.operand.ARMConstant;


public class LLVMGetelementptr extends LLVMInstruction
{
   public final LLVMField target;
   public final LLVMRegister source;
   public final int index;
   
   
   public LLVMGetelementptr(LLVMRegister source, LLVMType resultType, int index)
   {
      this.target = new LLVMField(resultType, source, index);
      this.source = source;
      this.index = index;
   }
   
   
   @Override
   public String llvmString()
   {
      return new StringBuilder(this.target.llvmString())
            .append(" = getelementptr ")
            .append(this.source.llvmTypedString())
            .append(", i1 0, i32 ")
            .append(this.index)
            .toString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      ;
   }
}
