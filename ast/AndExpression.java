package ast;


public class AndExpression
   extends LogicalExpression
{
   public AndExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
