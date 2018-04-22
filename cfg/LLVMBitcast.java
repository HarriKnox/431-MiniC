package cfg;


class LLVMBitcast
   implements LLVMInstruction
{
   LLVMRegister mallocked;
   LLVMRegister result;
   String struct;
   
   
   LLVMBitcast(LLVMRegister mallocked, LLVMRegister result, String struct)
   {
      this.mallocked = mallocked;
      this.result = result;
      this.struct = struct;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = bitcast i8* " +
            this.mallocked.toString() + " to %struct." + this.struct;
   }
}
