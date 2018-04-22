package ast;

public class LvalueDot
   extends LinedElement
   implements Lvalue
{
   public final Expression left;
   public final String id;
   public StructType type;

   public LvalueDot(int lineNum, Expression left, String id)
   {
      super(lineNum);
      this.left = left;
      this.id = id;
   }
}
