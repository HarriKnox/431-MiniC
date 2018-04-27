package ast.expression;

public class IntExpression
   extends Expression
{
   public final String value;

   public IntExpression(int lineNum, String value)
   {
      super(lineNum);
      this.value = value;
   }
}
