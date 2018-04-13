package ast;

public class NewExpression
   extends AbstractExpression
{
   public final String id;

   public NewExpression(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
