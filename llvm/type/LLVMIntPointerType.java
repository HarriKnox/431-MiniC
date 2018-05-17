package llvm.type;


public class LLVMIntPointerType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMIntPointerType;
   }
   
   
   @Override
   public String llvmString()
   {
      return "i32*";
   }
}
