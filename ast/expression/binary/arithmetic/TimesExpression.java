package ast.expression.binary.arithmetic;


import ast.expression.Expression;


public class TimesExpression extends ArithmeticExpression
{
   public TimesExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "times";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMTimes(l, r);
   }
}
