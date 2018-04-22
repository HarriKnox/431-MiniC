package cfg;


class LLVMFree
   implements LLVMInstruction;
{
   LLVMRegister pointer;
   
   
   LLVMFree(LLVMRegister ptr)
   {
      this.pointer = ptr;
   }
   
   
   public String toString()
   {
      return "call void @free(i8* " + this.pointer.toString() + ")";
   }
}
