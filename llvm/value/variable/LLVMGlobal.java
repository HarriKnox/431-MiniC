package llvm.value.variable;


import llvm.type.LLVMIntType;
import llvm.type.LLVMPointerType;
import llvm.type.LLVMType;


public class LLVMGlobal extends LLVMVariable
{
   public final String identifier;
   
   
   public LLVMGlobal(String id, LLVMType type)
   {
      super(type);
      
      this.identifier = id;
   }
   
   
   @Override
   public String llvmString()
   {
      return '@' + this.identifier;
   }
   
   
   /*
    * Despite the fact that these are in the 'variable' package, we are
    * defining constants here because they are global variables.
    */
   public static final LLVMGlobal SCANF_SCRATCH = new LLVMGlobal(
         ".scanf_scratch", new LLVMIntType());
}
