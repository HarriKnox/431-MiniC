package ast;


public class EqualsExpression
   extends EqualityExpression
{
   public EqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
