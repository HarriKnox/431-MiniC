package ast.statement;


import ast.expression.Expression;


public class WhileStatement extends Statement
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
