package ast;


public class OrExpression
   extends LogicalExpression
{
   public OrExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
