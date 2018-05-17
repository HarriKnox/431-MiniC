package llvm.type;


public class LLVMByteType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMByteType;
   }
   
   
   @Override
   public String astString()
   {
      return "int";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i8";
   }
   
   
   @Override
   public String defaultValue()
   {
      return "0";
   }
}
