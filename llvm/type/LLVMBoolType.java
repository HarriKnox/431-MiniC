package llvm.type;


public class LLVMBoolType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMBoolType;
   }
}
