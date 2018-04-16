package ast;


import org.antlr.v4.runtime.Token;


public class PrintStatement
   extends AbstractStatement
{
   private final Expression expression;

   public PrintStatement(Token token, Expression expression)
   {
      super(token);
      this.expression = expression;
   }
}
