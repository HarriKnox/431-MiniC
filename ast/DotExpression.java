package ast;

public class DotExpression
   extends AbstractExpression
{
   public final Expression left;
   public final String id;
   public StructType type;

   public DotExpression(int lineNum, Expression left, String id)
   {
      super(lineNum);
      this.left = left;
      this.id = id;
   }
}
