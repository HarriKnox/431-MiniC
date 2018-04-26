package ast.expression.binary.arithmetic;

import ast.expression.Expression;

public class PlusExpression
   extends ArithmeticExpression
{
   public PlusExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
