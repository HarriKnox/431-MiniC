package llvm.value;


import llvm.type.IntType;
import llvm.type.Type;


public class LLVMInt extends LLVMValue
{
   public final String value;
   
   
   public LLVMInt(String value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
}
