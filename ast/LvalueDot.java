package ast;


import org.antlr.v4.runtime.Token;


public class LvalueDot
   extends LinedElement
   implements Lvalue
{
   private final Expression left;
   private final String id;

   public LvalueDot(Token token, Expression left, String id)
   {
      super(token);
      this.left = left;
      this.id = id;
   }
}
