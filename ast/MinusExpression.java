package ast;


public class MinusExpression
   extends ArithmeticExpression
{
   public MinusExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
