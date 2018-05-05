package llvm.type;


public class LLVMBoolType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMBoolType;
   }
   
   
   @Override
   public String astString()
   {
      return "bool";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i1";
   }
   
   
   @Override
   public String llvmString()
   {
      return "false";
   }
}
