package ast.expression.unary;

import ast.expression.Expression;

public class NegateExpression
   extends UnaryExpression
{
   public NegateExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
}
