package ast;


public class NotEqualsExpression
   extends EqualityExpression
{
   public NotEqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
