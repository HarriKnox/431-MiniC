package ast;


import org.antlr.v4.runtime.Token;


public class WhileStatement
   extends AbstractStatement
{
   private final Expression guard;
   private final Statement body;

   public WhileStatement(Token token, Expression guard, Statement body)
   {
      super(token);
      this.guard = guard;
      this.body = body;
   }
   
   
   public Expression getGuard()
   {
      return this.guard;
   }
   
   
   public Statement getBody()
   {
      return this.body;
   }
}
