package llvm.type;


public class LLVMVoidType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMVoidType;
   }
}
