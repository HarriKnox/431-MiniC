package ast;

public class PrintStatement
   extends AbstractStatement
{
   public final Expression expression;

   public PrintStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
