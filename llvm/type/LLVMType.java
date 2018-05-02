package llvm.type;


public abstract class LLVMType
{
   public abstract boolean equivalent(Type type);
   
   public abstract String astString();
   
   public abstract String llvmString();
}
