package llvm.instruction.targeted;


import llvm.value.operand.LLVMOperand;


public class LLVMNot extends LLVMTargetedInstruction
{
   public final LLVMOperand source;
   
   
   public LLVMNot(LLVMOperand source)
   {
      super(source.type);
      
      this.source = source;
   }
   
   
   @Override
   protected String getInstruction()
   {
      return "xor i1 true, " + this.source.llvmString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.add(new ARMEor(this.source.buildARM(node), new ARMConstant(1)));
   }
}
