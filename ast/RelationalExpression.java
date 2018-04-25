package ast;


public abstract class RelationalExpression
   extends BinaryExpression
{
   public RelationalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
