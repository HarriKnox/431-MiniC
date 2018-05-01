package ast.expression;


import java.util.List;


public class InvocationExpression extends Expression
{
   public final String name;
   public final List<Expression> arguments;


   public InvocationExpression(
         int lineNum,
         String name,
         List<Expression> arguments)
   {
      super(lineNum, getMax(arguments));

      this.name = name;
      this.arguments = arguments;
   }
   
   
   private static int getMax(List<Expression> args)
   {
      int max = 0;
      
      for (Expression exp : args)
         if (exp.height > max)
            max = exp.height;
      
      return max;
   }
}
