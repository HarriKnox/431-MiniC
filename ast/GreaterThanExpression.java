package ast;


public class GreaterThanExpression
   extends RelationalExpression
{
   public GreaterThanExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
