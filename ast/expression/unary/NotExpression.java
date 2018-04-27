package ast.expression.unary;


import ast.expression.Expression;


public class NotExpression extends UnaryExpression
{
   public NotExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
}
