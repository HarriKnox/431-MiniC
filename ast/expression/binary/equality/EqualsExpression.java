package ast.expression.binary.equality;


import ast.expression.Expression;


public class EqualsExpression extends EqualityExpression
{
   public EqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
