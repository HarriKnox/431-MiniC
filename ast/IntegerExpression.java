package ast;

public class IntegerExpression
   extends Expression
{
   public final String value;

   public IntegerExpression(int lineNum, String value)
   {
      super(lineNum);
      this.value = value;
   }
}
