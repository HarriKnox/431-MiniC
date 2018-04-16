package ast;


import java.util.List;

import org.antlr.v4.runtime.Token;


public class InvocationExpression
   extends AbstractExpression
{
   private final String name;
   private final List<Expression> arguments;

   public InvocationExpression(Token token, String name,
      List<Expression> arguments)
   {
      super(token);
      this.name = name;
      this.arguments = arguments;
   }
}
