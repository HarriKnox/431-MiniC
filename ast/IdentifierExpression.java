package ast;

public class IdentifierExpression
   extends AbstractExpression
{
   public final String id;

   public IdentifierExpression(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
