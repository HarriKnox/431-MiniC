package ast.expression.binary.arithmetic;

import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;

public abstract class ArithmeticExpression
   extends BinaryExpression
{
   public ArithmeticExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
