package ast.statement;


import java.util.LinkedList;
import java.util.List;


public class BlockStatement extends Statement
{
   public final List<Statement> statements;


   public BlockStatement(int lineNum, List<Statement> statements)
   {
      super(lineNum);

      this.statements = statements;
   }


   public boolean isEmpty()
   {
      return this.statements.isEmpty();
   }
}
