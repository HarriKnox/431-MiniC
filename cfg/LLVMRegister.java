package cfg;


class LLVMRegister
{
   static int count = 0;
   
   String name;
   
   
   LLVMRegister()
   {
      this.name = "%u" + count++;
   }
   
   
   LLVMRegister(String name)
   {
      this.name = name;
   }
}
