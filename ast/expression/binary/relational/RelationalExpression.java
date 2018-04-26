package ast.expression.binary.relational;

import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;

public abstract class RelationalExpression
   extends BinaryExpression
{
   public RelationalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
