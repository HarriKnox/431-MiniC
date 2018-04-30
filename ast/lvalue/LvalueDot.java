package ast.lvalue;


public class LvalueDot extends Lvalue
{
   public final Lvalue left;
   public final String id;


   public LvalueDot(int lineNum, Lvalue left, String id)
   {
      super(lineNum, left.height + 1);

      this.left = left;
      this.id = id;
   }
}
