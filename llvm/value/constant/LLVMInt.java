package llvm.value.constant;


import llvm.type.IntType;
import llvm.type.Type;


public class LLVMInt extends LLVMConstant
{
   public final String value;
   
   
   public LLVMInt(String value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
}
