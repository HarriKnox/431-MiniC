package ast;

public class IdentifierExpression
   extends AbstractExpression
{
   public final String id;
   public String funcName;
   public Type type;

   public IdentifierExpression(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
