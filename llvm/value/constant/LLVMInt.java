package llvm.value.constant;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


public class LLVMInt extends LLVMConstant
{
   public final int value;
   
   
   public LLVMInt(int value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return Integer.toString(this.value);
   }
}
