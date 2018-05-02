package llvm.type;


public class LLVMStructType extends LLVMType
{
   public final String name;


   public LLVMStructType(String name)
   {
      this.name = name;
   }


   @Override
   public boolean equivalent(LLVMType t)
   {
      return (t instanceof LLVMNullType)
            || ((t instanceof LLVMStructType)
                  && this.name.equals(((LLVMStructType)t).name));
   }
   
   
   @Override
   public String astString()
   {
      return "struct " + this.name;
   }
   
   
   @Override
   public String llvmString()
   {
      return "%struct." + this.name + "*";
   }
}
