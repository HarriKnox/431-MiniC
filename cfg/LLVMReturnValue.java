package cfg;


class LLVMReturnValue
   implements LLVMValue
{
   String funcName;
   
   
   LLVMReturnValue(String funcName)
   {
      this.funcName = funcName;
   }
   
   
   public String toString()
   {
      return "%" + this.funcName + ".return.value";
   }
}
