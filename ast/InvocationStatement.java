package ast;

public class InvocationStatement
   extends AbstractStatement
{
   public final Expression expression;

   public InvocationStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
