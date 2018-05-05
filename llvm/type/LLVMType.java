package llvm.type;


public abstract class LLVMType
{
   public abstract boolean equivalent(LLVMType type);
   
   public abstract String astString();
   
   public abstract String llvmString();
   
   public String defaultValue()
   {
      return "";
   }
}
