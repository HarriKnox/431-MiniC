package ast;


public class LessThanExpression
   extends RelationalExpression
{
   public LessThanExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
