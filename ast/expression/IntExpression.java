package ast.expression;


public class IntExpression extends Expression
{
   public final String value;
   public final boolean negative;


   public IntExpression(int lineNum, String value)
   {
      this(lineNum, value, false);
   }
   
   
   private IntExpression(int lineNum, String value, boolean negative)
   {
      super(lineNum, 0);

      this.value = value;
      this.negative = negative;
   }
   
   
   public IntExpression negate()
   {
      return new IntExpression(this.lineNum, this.value, !this.negative);
   }
}
