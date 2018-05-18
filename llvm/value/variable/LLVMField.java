package llvm.value.variable;


import llvm.type.LLVMType;


public class LLVMField extends LLVMVariable
{
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMField(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "%f" + Integer.toString(this.getUID());
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
}
