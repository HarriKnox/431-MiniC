package ast;

public class WhileStatement
   extends AbstractStatement
{
   public final Expression guard;
   public final Statement body;

   public WhileStatement(int lineNum, Expression guard, Statement body)
   {
      super(lineNum);
      this.guard = guard;
      this.body = body;
   }
}
