package ast;

public class LvalueId
   extends LinedElement
   implements Lvalue
{
   private final String id;

   public LvalueId(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
