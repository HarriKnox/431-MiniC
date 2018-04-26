package ast.expression.binary.logical;

import ast.expression.Expression;

public class OrExpression
   extends LogicalExpression
{
   public OrExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
