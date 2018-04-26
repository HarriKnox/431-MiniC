package ast.expression;


public class DotExpression
   extends Expression
{
   public final Expression left;
   public final String id;

   public DotExpression(int lineNum, Expression left, String id)
   {
      super(lineNum);
      this.left = left;
      this.id = id;
   }
}