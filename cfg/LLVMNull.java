package cfg;


class LLVMNull
   implements LLVMValue
{
   static final LLVMNull NULL = new LLVMNull();
   
   
   private LLVMNull()
   {
      ;
   }
   
   
   public String toString()
   {
      return "null";
   }
}
