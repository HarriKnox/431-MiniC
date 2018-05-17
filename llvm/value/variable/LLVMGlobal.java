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
   
   
   public static final LLVMGlobal SCANF_SCRATCH = new LLVMGlobal(
         ".scanf_scratch", new LLVMPointerType(new LLVMIntType()));
}
