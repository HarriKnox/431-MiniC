package llvm.instruction.targeted;


import llvm.value.variable.LLVMVariable;


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
}
