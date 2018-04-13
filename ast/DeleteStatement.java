package ast;

public class DeleteStatement
   extends AbstractStatement
{
   public final Expression expression;

   public DeleteStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
