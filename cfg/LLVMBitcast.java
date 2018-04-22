package cfg;


class LLVMBitcast
   implements LLVMInstruction
{
   LLVMRegister from;
   LLVMRegister result;
   String struct;
   String fromType;
   
   
   LLVMBitcast(LLVMRegister from, LLVMRegister result, String fromType, String struct)
   {
      this.from = from;
      this.result = result;
      this.struct = struct;
      this.fromType = fromType;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = bitcast " + this.fromType + " " +
            this.from.toString() + " to %struct." + this.struct;
   }
}
