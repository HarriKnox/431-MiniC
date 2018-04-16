package ast;


import org.antlr.v4.runtime.Token;


public class IdentifierExpression
   extends AbstractExpression
{
   private final String id;

   public IdentifierExpression(Token token, String id)
   {
      super(token);
      this.id = id;
   }
}
