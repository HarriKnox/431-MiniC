package cfg;


class LLVMBoolean
   implements LLVMValue
{
   boolean value;
   
   
   private LLVMBoolean(Boolean value)
   {
      this.value = value;
   }
   
   
   public toString()
   {
      return Boolean.toString(this.value);
   }
   
   
   static final LLVMBoolean TRUE  = new LLVMValue(true);
   static final LLVMBoolean FALSE = new LLVMValue(false);
}
