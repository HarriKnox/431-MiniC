package ast.lvalue;


public class LvalueId extends Lvalue
{
   public final String id;


   public LvalueId(int lineNum, String id)
   {
      super(lineNum);

      this.id = id;
   }
}
