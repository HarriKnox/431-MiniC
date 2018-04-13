package ast;

public class ReturnStatement
   extends AbstractStatement
{
   public final Expression expression;

   public ReturnStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
