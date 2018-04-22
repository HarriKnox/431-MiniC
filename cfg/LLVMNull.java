package cfg;


class LLVMNull
   implements LLVMValue
{
   static final LLVMNull NULL = new LLVMNull();
   
   
   private LLVMNull()
   {
      ;
   }
   
   
   public toString()
   {
      return "null";
   }
}
