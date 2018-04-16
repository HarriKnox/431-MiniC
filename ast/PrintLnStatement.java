package ast;


import org.antlr.v4.runtime.Token;


public class PrintLnStatement
   extends AbstractStatement
{
   private final Expression expression;

   public PrintLnStatement(Token token, Expression expression)
   {
      super(token);
      this.expression = expression;
   }
}
