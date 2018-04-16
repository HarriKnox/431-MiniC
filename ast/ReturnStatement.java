package ast;


import org.antlr.v4.runtime.Token;


public class ReturnStatement
   extends AbstractStatement
{
   private final Expression expression;

   public ReturnStatement(Token token, Expression expression)
   {
      super(token);
      this.expression = expression;
   }
}
