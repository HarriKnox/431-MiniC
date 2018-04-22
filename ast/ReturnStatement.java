package ast;

public class ReturnStatement
   extends AbstractStatement
{
   public final Expression expression;
   public Type type;

   public ReturnStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
