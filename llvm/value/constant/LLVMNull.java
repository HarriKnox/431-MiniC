package llvm.value.constant;


import llvm.type.LLVMNullType;
import llvm.type.LLVMType;


public class LLVMNull extends LLVMConstant
{
   public LLVMNull()
   {
      super(new LLVMNullType());
   }
}
