package llvm.type;


public abstract class LLVMType
{
   public abstract boolean equivalent(LLVMType type);
   
   public abstract String llvmString();
   
   
   public String astString()
   {
      return "";
   }
   
   
   public String defaultValue()
   {
      return "";
   }
}
