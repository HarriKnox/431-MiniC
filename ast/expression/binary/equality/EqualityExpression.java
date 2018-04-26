package ast.expression.binary.equality;

import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;

public abstract class EqualityExpression
   extends BinaryExpression
{
   public EqualityExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
