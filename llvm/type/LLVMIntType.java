package llvm.type;


public class LLVMIntType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMIntType;
   }
   
   
   @Override
   public String astString()
   {
      return "int";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i32";
   }
   
   
   @Override
   public String defaultValue()
   {
      return "0";
   }
}
