package ast;


import org.antlr.v4.runtime.Token;


public class InvocationStatement
   extends AbstractStatement
{
   private final Expression expression;

   public InvocationStatement(Token token, Expression expression)
   {
      super(token);
      this.expression = expression;
   }
}
