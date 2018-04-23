package ast;

import java.util.List;

public class InvocationExpression
   extends Expression
{
   public final String name;
   public final List<Expression> arguments;

   public InvocationExpression(int lineNum, String name,
      List<Expression> arguments)
   {
      super(lineNum);
      this.name = name;
      this.arguments = arguments;
   }
}
