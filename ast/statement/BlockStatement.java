package ast.statement;

import java.util.List;
import java.util.ArrayList;

public class BlockStatement
   extends Statement
{
   public final List<Statement> statements;

   public BlockStatement(int lineNum, List<Statement> statements)
   {
      super(lineNum);
      this.statements = statements;
   }

   public static BlockStatement emptyBlock()
   {
      return new BlockStatement(-1, new ArrayList<>());
   }
}
