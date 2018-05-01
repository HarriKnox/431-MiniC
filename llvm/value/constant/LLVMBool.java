package llvm.value.constant;


import llvm.type.NullType;
import llvm.type.Type;


public class LLVMBool extends LLVMConstant
{
   public final boolean value;
   
   
   public LLVMBool(boolean value)
   {
      super(new LLVMBoolType());
      
      this.value = value;
   }
}
