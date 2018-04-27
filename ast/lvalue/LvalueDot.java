package ast.lvalue;


public class LvalueDot extends Lvalue
{
   public final Lvalue left;
   public final String id;


   public LvalueDot(int lineNum, Lvalue left, String id)
   {
      super(lineNum);

      this.left = left;
      this.id = id;
   }
}
