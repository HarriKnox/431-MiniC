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
   
   
   public void setUID()
   {
      if (this.uid == -1)
         this.uid = count++;
   }
}
