package ast;

public abstract class Expression
   extends LinedElement
{
   public Type type;
   
   public Expression(int lineNum)
   {
      super(lineNum);
   }
}
