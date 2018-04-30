package ast.expression;


public class NewExpression extends Expression
{
   public final String id;


   public NewExpression(int lineNum, String id)
   {
      super(lineNum, 0);
      this.id = id;
   }
}
