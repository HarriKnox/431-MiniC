package llvm.value.variable;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


public class LLVMGlobal extends LLVMVariable
{
   public final String name;
   
   
   public LLVMGlobal(String name, LLVMType type)
   {
      super(type);
      
      this.name = name;
   }
   
   
   @Override
   public String llvmString()
   {
      return '@' + this.name;
   }
   
   
   @Override
   public ARMAddress buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMGlobal(this.name));
      ARMMovt movt = new ARMMovt(movw.target, movw.value);
      
      node.add(movw).add(movt);
      
      return new ARMAddress(movw.target, 0);
   }
   
   
   public static final LLVMGlobal SCANF_SCRATCH = new LLVMGlobal(
         ".scanf_scratch", new LLVMIntType());
}
