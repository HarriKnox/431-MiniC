package ast.statement;


import ast.expression.Expression;


public class InvocationStatement extends Statement
{
   public final Expression expression;


   public InvocationStatement(int lineNum, Expression expression)
   {
      super(lineNum);

      this.expression = expression;
   }
}
