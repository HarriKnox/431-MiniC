package cfg;


class LLVMInteger
   implements LLVMValue
{
   int value;
   
   LLVMInteger(String val)
   {
      this.value = Integer.parseInt(val);
   }
   
   
   public toString()
   {
      return Integer.toString(this.value);
   }
}
