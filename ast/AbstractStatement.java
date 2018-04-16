package ast;


import org.antlr.v4.runtime.Token;


public abstract class AbstractStatement
   extends LinedElement
   implements Statement
{
   public AbstractStatement(Token token)
   {
      super(token);
   }
}
