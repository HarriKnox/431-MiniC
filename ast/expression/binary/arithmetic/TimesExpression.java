package ast.expression.binary.arithmetic;


import ast.expression.Expression;


public class TimesExpression extends ArithmeticExpression
{
   public TimesExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
