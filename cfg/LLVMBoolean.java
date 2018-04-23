package cfg;


class LLVMBoolean
   implements LLVMValue
{
   boolean value;
   
   
   private LLVMBoolean(Boolean value)
   {
      this.value = value;
   }
   
   
   public String toString()
   {
      return Boolean.toString(this.value);
   }
   
   
   static final LLVMBoolean TRUE  = new LLVMBoolean(true);
   static final LLVMBoolean FALSE = new LLVMBoolean(false);
}
