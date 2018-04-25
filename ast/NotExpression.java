package ast;

public class NotExpression
   extends UnaryExpression
{
   public NotExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
}
