package ast;


import org.antlr.v4.runtime.Token;


public class AssignmentStatement
   extends AbstractStatement
{
   private final Lvalue target;
   private final Expression source;

   public AssignmentStatement(Token token, Lvalue target, Expression source)
   {
      super(token);
      this.target = target;
      this.source = source;
   }
}
