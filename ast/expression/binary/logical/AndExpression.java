package ast.expression.binary.logical;

import ast.expression.Expression;

public class AndExpression
   extends LogicalExpression
{
   public AndExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
