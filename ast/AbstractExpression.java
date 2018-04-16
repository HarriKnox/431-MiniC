package ast;


import org.antlr.v4.runtime.Token;


public abstract class AbstractExpression
   extends LinedElement
   implements Expression
{
   public AbstractExpression(Token token)
   {
      super(token);
   }
}
