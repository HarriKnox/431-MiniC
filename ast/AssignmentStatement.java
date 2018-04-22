package ast;

public class AssignmentStatement
   extends AbstractStatement
{
   public final Lvalue target;
   public final Expression source;
   public Type type;

   public AssignmentStatement(int lineNum, Lvalue target, Expression source)
   {
      super(lineNum);
      this.target = target;
      this.source = source;
   }
}
