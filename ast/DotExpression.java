package ast;


import org.antlr.v4.runtime.Token;


public class DotExpression
   extends AbstractExpression
{
   private final Expression left;
   private final String id;

   public DotExpression(Token token, Expression left, String id)
   {
      super(token);
      this.left = left;
      this.id = id;
   }
}
