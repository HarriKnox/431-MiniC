package llvm.value;


import llvm.type.NullType;
import llvm.type.Type;


public class LLVMNull extends LLVMValue
{
   @Override
   public LLVMType getType()
   {
      return new LLVMNullType();
   }
}
