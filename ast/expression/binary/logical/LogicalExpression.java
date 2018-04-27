package ast.expression.binary.logical;


import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;


public abstract class LogicalExpression extends BinaryExpression
{
   public LogicalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
