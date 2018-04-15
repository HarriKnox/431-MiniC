package ast;

public class LvalueDot
   extends LinedElement
   implements Lvalue
{
   private final Expression left;
   private final String id;

   public LvalueDot(int lineNum, Expression left, String id)
   {
      super(lineNum);
      this.left = left;
      this.id = id;
   }
}
