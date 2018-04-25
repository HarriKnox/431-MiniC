package ast;


public class DivideExpression
   extends ArithmeticExpression
{
   public DivideExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
