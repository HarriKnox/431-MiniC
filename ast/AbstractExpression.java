package ast;

public abstract class AbstractExpression
   extends LinedElement
   implements Expression
{
   public AbstractExpression(int lineNum)
   {
      super(lineNum);
   }
}
