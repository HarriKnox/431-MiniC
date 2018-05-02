package llvm.type;


public class LLVMPointerType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMPointerType;
   }
   
   
   @Override
   public String astString()
   {
      return "null";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i8*";
   }
}
