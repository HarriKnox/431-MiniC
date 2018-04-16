package ast;


import org.antlr.v4.runtime.Token;


public class LvalueId
   extends LinedElement
   implements Lvalue
{
   private final String id;

   public LvalueId(Token token, String id)
   {
      super(token);
      this.id = id;
   }
}
