package ast.expression.binary.arithmetic;


import ast.expression.Expression;


public class DivideExpression extends ArithmeticExpression
{
   public DivideExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
