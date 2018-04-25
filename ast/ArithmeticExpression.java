package ast;


public abstract class ArithmeticExpression
   extends BinaryExpression
{
   public ArithmeticExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
