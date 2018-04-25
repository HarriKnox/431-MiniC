package ast;


public class LessEqualsExpression
   extends RelationalExpression
{
   public LessEqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
