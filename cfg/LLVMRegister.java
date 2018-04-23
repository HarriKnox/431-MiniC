package cfg;


class LLVMRegister
   implements LLVMValue
{
   static int count = 0;
   
   String name;
   
   
   LLVMRegister()
   {
      this.name = "h" + count++;
   }
   
   
   LLVMRegister(String name)
   {
      this.name = name;
   }
   
   
   public String toString()
   {
      return "%" + this.name;
   }
}
