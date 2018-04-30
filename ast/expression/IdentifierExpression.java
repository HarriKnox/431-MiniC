package ast.expression;


public class IdentifierExpression extends Expression
{
   public final String id;


   public IdentifierExpression(int lineNum, String id)
   {
      super(lineNum, 0);

      this.id = id;
   }
}
