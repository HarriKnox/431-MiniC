package cfg;


import ast.UnaryExpression;


class LLVMUnary
   implements LLVMInstruction
{
   LLVMValue value;
   LLVMRegister result;
   UnaryExpression.Operator operator;
   
   
   LLVMUnary(LLVMValue value, LLVMRegister result, UnaryExpression.Operator operator)
   {
      this.value = value;
      this.result = result;
      this.operator = operator;
   }
   
   
   private String operationString()
   {
      if (this.operator == UnaryExpression.Operator.MINUS)
         return "sub i32 0, " + this.value.toString();
      
      return "xor i1 " + this.value.toString() + ", " + LLVMBoolean.TRUE.toString();
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = " + this.operationString();
   }
}
