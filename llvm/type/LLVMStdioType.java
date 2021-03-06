package llvm.type;


public class LLVMStdioType extends LLVMType
{
   @Override
   public boolean equivalent(LLVMType t)
   {
      return (t instanceof LLVMStdioType);
   }
   
   
   @Override
   public String astString()
   {
      return "stdio";
   }
   
   
   @Override
   public String llvmString()
   {
      return "i32 (i8*, ...)*";
   }
}
