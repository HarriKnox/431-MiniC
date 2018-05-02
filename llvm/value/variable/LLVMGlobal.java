package llvm.value.variable;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


public class LLVMGlobal extends LLVMVariable
{
   public final String identifier;
   
   
   public LLVMGlobal(String id, LLVMType type)
   {
      super(type);
      
      this.identifier = id;
   }
   
   
   /*
    * Despite the fact that this is in the 'variable' package, we are defining
    * a constant here because it is a global variable.
    */
   public final LLVMGlobal SCANF_SCRATCH = new LLVMGlobal(
         ".scanf_scratch", new LLVMIntType());
   
   
   /* '@' + this.identifier */
}
