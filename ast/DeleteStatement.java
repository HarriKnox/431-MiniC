package ast;


import org.antlr.v4.runtime.Token;


public class DeleteStatement
   extends AbstractStatement
{
   private final Expression expression;

   public DeleteStatement(Token token, Expression expression)
   {
      super(token);
      this.expression = expression;
   }
}
