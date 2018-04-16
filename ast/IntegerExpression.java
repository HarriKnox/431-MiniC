package ast;


import org.antlr.v4.runtime.Token;


public class IntegerExpression
   extends AbstractExpression
{
   private final String value;

   public IntegerExpression(Token token, String value)
   {
      super(token);
      this.value = value;
   }
}
