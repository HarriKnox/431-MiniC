package ast.expression;

public class NegateExpression
   extends UnaryExpression
{
   public NegateExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
}
