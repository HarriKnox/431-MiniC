package ast;

public class PrintLnStatement
   extends AbstractStatement
{
   public final Expression expression;

   public PrintLnStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
