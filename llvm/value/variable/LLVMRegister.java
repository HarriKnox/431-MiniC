package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMRegister extends LLVMVariable
{
   private int uid = -1;
   
   
   private static int count = 0;
   
   
   public LLVMRegister(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "%v" + Integer.toString(this.uid);
   }
   
   
   public void setUID()
   {
      if (this.uid == -1)
         this.uid = count++;
   }
}
