package llvm.value.constant;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


public class LLVMInt extends LLVMConstant
{
   public final String value;
   
   
   public LLVMInt(String value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
}
