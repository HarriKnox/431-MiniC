package llvm.type;


public class LLVMStructType extends LLVMType
{
   public final String name;


   public StructType(String name)
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
}
