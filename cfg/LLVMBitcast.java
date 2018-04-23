package cfg;


class LLVMBitcast
   implements LLVMInstruction
{
   LLVMValue from;
   LLVMRegister result;
   String toType;
   String fromType;
   
   
   LLVMBitcast(LLVMValue from, LLVMRegister result, String fromType, String toType)
   {
      this.from = from;
      this.result = result;
      this.toType = toType;
      this.fromType = fromType;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = bitcast " + this.fromType + " " +
            this.from.toString() + " to " + this.toType;
   }
}
