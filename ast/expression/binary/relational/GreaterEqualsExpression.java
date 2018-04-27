package ast.expression.binary.relational;


import ast.expression.Expression;


public class GreaterEqualsExpression extends RelationalExpression
{
   public GreaterEqualsExpression(
         int lineNum,
         Expression left,
         Expression right)
   {
      super(lineNum, left, right);
   }
}
