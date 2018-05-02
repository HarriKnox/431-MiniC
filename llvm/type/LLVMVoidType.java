package llvm.type;


public class LLVMVoidType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMVoidType;
   }
   
   
   @Override
   public String astString()
   {
      return "void";
   }
   
   
   @Override
   public String llvmString()
   {
      return "void";
   }
}
