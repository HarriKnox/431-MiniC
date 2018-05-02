package llvm.type;


public class LLVMNullType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return (t instanceof LLVMNullType) || (t instanceof LLVMStructType);
   }
   
   
   @Override
   public String astString()
   {
      return "null";
   }
   
   
   @Override
   public String llvmString()
   {
      return "null";
   }
}
