package ast;


import org.antlr.v4.runtime.Token;


public class Declaration
   extends LinedElement
{
   private final Type type;
   private final String name;

   public Declaration(Token token, Type type, String name)
   {
      super(token);
      this.type = type;
      this.name = name;
   }
}
