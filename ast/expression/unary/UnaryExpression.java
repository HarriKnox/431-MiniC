package ast.expression.unary;

import ast.expression.Expression;

public class UnaryExpression
   extends Expression
{
   public final Expression operand;

   public UnaryExpression(int lineNum, Expression operand)
   {
      super(lineNum);
      
      this.operand = operand;
   }
}
