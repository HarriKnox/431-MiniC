package llvm.type;


public class LLVMIntType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return t instanceof LLVMIntType;
   }
}
