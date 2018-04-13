package ast;

public class LvalueId
   extends LinedElement
   implements Lvalue
{
   public final String id;

   public LvalueId(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
