package ast;


import java.util.List;
import java.util.ArrayList;

import org.antlr.v4.runtime.Token;


public class BlockStatement
   extends AbstractStatement
{
   private final List<Statement> statements;

   public BlockStatement(Token token, List<Statement> statements)
   {
      super(token);
      this.statements = statements;
   }

   public static BlockStatement emptyBlock()
   {
      return new BlockStatement(-1, new ArrayList<>());
   }
}
