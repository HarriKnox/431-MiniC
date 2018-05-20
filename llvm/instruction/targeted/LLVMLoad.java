package llvm.instruction.targeted;


import llvm.value.variable.LLVMVariable;

import arm.ARMCFGNode;

import arm.instruction.ARMLdr;


public class LLVMLoad extends LLVMTargetedInstruction
{
   public final LLVMVariable source;
   
   
   public LLVMLoad(LLVMVariable source)
   {
      super(source.type);
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return new StringBuilder("load ")
            .append(this.source.type.llvmString())
            .append("* ")
            .append(this.source.llvmString())
            .toString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMLdr(
            this.target.buildARM(node),
            this.source.buildARM(node)));
   }
}
