package ast.statement;

import ast.expression.Expression;

public class ConditionalStatement
   extends Statement
{
   public final Expression guard;
   public final Statement thenBlock;
   public final Statement elseBlock;

   public ConditionalStatement(int lineNum, Expression guard,
      Statement thenBlock, Statement elseBlock)
   {
      super(lineNum);
      this.guard = guard;
      this.thenBlock = thenBlock;
      this.elseBlock = elseBlock;
   }
}
