package llvm.value.operand.constant;


import llvm.type.LLVMBoolType;


public class LLVMBool extends LLVMConstant
{
   public final boolean value;
   
   
   public LLVMBool(boolean value)
   {
      super(new LLVMBoolType());
      
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return this.value ? "true" : "false";
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMInt(this.value ? 1 : 0));
      node.add(movw);
      
      return movw.target;
   }
}
