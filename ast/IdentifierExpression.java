package ast;

public class IdentifierExpression
   extends Expression
{
   public final String id;
   public String funcName;
   public boolean global = false;

   public IdentifierExpression(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
