package ast;


import org.antlr.v4.runtime.Token;


public abstract class LinedElement
{
   private final Token token;
   
   public LinedElement(Token token)
   {
      this.token = token;
   }
   
   public Token getToken()
   {
      return this.token;
   }
}
