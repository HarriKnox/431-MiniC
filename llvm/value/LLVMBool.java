package llvm.value;


import llvm.type.NullType;
import llvm.type.Type;


public class LLVMBool extends LLVMValue
{
   public final boolean value;
   
   
   public LLVMBool(boolean value)
   {
      this.value = value;
   }
   
   
   @Override
   public LLVMType getType()
   {
      return new LLVMBoolType();
   }
}
