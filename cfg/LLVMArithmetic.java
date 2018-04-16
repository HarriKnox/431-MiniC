package cfg;


class LLVMArithmetic
   implements LLVMInstruction
{
   Register operandLeft;
   Register operandRight;
   Register result;
   Operation operation;
   
   
   LLVMArithmetic(Register oL, Register oR, Register res, Operation op)
   {
      this.operandLeft = oL;
      this.operandRight = oR;
      this.result = res;
      this.operation = op;
   }
   
   
   private static final String TIMES_OPERATOR = "mul";
   private static final String DIVIDE_OPERATOR = "sdiv";
   private static final String PLUS_OPERATOR = "add";
   private static final String MINUS_OPERATOR = "sub";
   
   public enum Operation
   {
      TIMES(TIMES_OPERATOR),
      DIVIDE(DIVIDE_OPERATOR),
      PLUS(PLUS_OPERATOR),
      MINUS(MINUS_OPERATOR);
      
      private final String opStr;
      
      Operator(String opStr)
      {
         this.opStr = opStr;
      }
      
      public String toString()
      {
         return this.opStr;
      }
   }
}
