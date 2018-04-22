package cfg;


class LLVMGlobal
   implements LLVMValue
{
   String name;
   
   
   LLVMGlobal(String name)
   {
      this.name = name;
   }
   
   
   public String toString()
   {
      return "@" + this.name;
   }
   
   
   static final LLVMGlobal READ_SCRATCH   = new LLVMGlobal(".read_scratch");
   static final LLVMGlobal READ_FORMAT    = new LLVMGlobal(".read_format");
   static final LLVMGlobal PRINT_FORMAT   = new LLVMGlobal(".print_format");
   static final LLVMGlobal PRINTLN_FORMAT = new LLVMGlobal(".println_format");
}
