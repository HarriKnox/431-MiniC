package ast.expression;


public class BoolExpression extends Expression
{
   public final boolean value;


   public BoolExpression(int lineNum, boolean value)
   {
      super(lineNum);

      this.value = value;
   }
}
