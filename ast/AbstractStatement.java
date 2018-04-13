package ast;

public abstract class AbstractStatement
   extends LinedElement
   implements Statement
{
   public AbstractStatement(int lineNum)
   {
      super(lineNum);
   }
}
