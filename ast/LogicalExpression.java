package ast;


public abstract class LogicalExpression
   extends BinaryExpression
{
   public LogicalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
