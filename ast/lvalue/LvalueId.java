package ast.lvalue;


public class LvalueId extends Lvalue
{
   public final String id;


   public LvalueId(int lineNum, String id)
   {
      super(lineNum, 0);

      this.id = id;
   }
}
