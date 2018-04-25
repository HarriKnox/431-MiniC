package ast;


public class GreaterEqualsExpression
   extends RelationalExpression
{
   public GreaterEqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
