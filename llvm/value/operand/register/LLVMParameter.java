package llvm.value.operand.register;


import llvm.type.LLVMType;


public class LLVMParameter extends LLVMRegister
{
   public final String function;
   public final String id;
   public final int index;
   
   
   public LLVMParameter(String function,
         String identifier, LLVMType type, int index)
   {
      super(type);
      
      this.function = function;
      this.id = identifier;
      this.index = index;
   }
   
   
   @Override
   public String llvmString()
   {
      return '%' + this.function + ".param." + this.id;
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      if (this.index <= 3)
         return ARMRegister.getReal(this.index);
      
      
      ARMLdr load = new ARMLdr(new ARMAddress(FP, (this.index - 3) * 4));
      
      node.add(load);
      
      return load.target;
   }
}
