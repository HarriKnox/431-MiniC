package ast;


import org.antlr.v4.runtime.Token;


public class ConditionalStatement
   extends AbstractStatement
{
   private final Expression guard;
   private final Statement thenBlock;
   private final Statement elseBlock;

   public ConditionalStatement(Token token, Expression guard,
      Statement thenBlock, Statement elseBlock)
   {
      super(token);
      this.guard = guard;
      this.thenBlock = thenBlock;
      this.elseBlock = elseBlock;
   }
}
