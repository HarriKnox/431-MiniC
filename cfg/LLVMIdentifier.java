package cfg;


class LLVMIdentifier
   implements LLVMValue
{
   String name;
   
   
   LLVMIdentifier(String func, String id)
   {
      this.name = "%" + func + "." + id;
   }
   
   
   public String toString()
   {
      return this.name;
   }
}
