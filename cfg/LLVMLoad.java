package cfg;


class LLVMLoad
   implements LLVMInstruction
{
   String retType;
   LLVMRegister result;
   LLVMValue source;
   
   
   LLVMLoad(LLVMRegister r, LLVMValue s, String rt)
   {
      this.result = r;
      this.source = s;
      this.retType = rt;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = load " + this.retType + "* " + this.source.toString();
   }
}
