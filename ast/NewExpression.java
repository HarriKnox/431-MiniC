package ast;


import org.antlr.v4.runtime.Token;


public class NewExpression
   extends AbstractExpression
{
   private final String id;

   public NewExpression(Token token, String id)
   {
      super(token);
      this.id = id;
   }
}
