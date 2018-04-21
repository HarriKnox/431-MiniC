package cfg;


class LLVMRegister
   implements LLVMValue
{
   static int count = 0;
   
   String name;
   
   
   LLVMRegister()
   {
      this.name = "u" + count++;
   }
   
   
   LLVMRegister(String name)
   {
      this.name = name;
   }
   
   
   public toString()
   {
      return "%" + this.name;
   }
}
