package ast;


import org.antlr.v4.runtime.Token;


public abstract class TokenedElement
{
   public final Token token;


   public TokenedElement(Token token)
   {
      this.token = token;
   }
}
