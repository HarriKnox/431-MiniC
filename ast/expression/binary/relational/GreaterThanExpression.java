package ast.expression.binary.relational;


import ast.expression.Expression;


public class GreaterThanExpression extends RelationalExpression
{
   public GreaterThanExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
