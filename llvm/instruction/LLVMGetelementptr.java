package llvm.instruction;


import llvm.value.LLVMValue;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;

import llvm.value.variable.LLVMField;


public class LLVMGetelementptr extends LLVMInstruction
{
   public final LLVMField target;
   public final LLVMVirtual source;
   public final int index;
   
   
   public LLVMGetelementptr(LLVMVirtual source, LLVMType resultType, int index)
   {
      this.target = new LLVMField(resultType);
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
      node.add(new ARMAdd(
            this.target.buildARM(node),
            this.source.buildARM(node),
            this.index * 4));
   }
}
