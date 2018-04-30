package llvm.value;


import llvm.type.NullType;
import llvm.type.Type;


public class LLVMNull extends LLVMValue
{
   public LLVMNull()
   {
      super(new LLVMNullType());
   }
}
