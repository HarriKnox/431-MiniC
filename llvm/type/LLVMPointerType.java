package llvm.type;


public class LLVMPointerType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMPointerType;
   }
}
