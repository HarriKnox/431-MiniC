package cfg;


class LLVMPrint
   implements LLVMInstruction
{
   LLVMValue expression;
   LLVMGlobal format;
   
   
   LLVMPrint(LLVMGlobal format, LLVMValue expression)
   {
      this.format = format;
      this.expression = expression;
   }
   
   
   public String toString()
   {
      return "call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* "
            + this.format.toString() + ", i32 0, i32 0), i32 "
            + this.expression.toString() + ")";
   }
}
