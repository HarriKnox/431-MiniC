package ast;

public class IntegerExpression
   extends AbstractExpression
{
   public final String value;

   public IntegerExpression(int lineNum, String value)
   {
      super(lineNum);
      this.value = value;
   }
}
