package ast;


public abstract class EqualityExpression
   extends BinaryExpression
{
   public EqualityExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
