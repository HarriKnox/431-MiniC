package ast.statement;


import ast.expression.Expression;


public class ReturnStatement extends Statement
{
   public final Expression expression;


   public ReturnStatement(int lineNum, Expression expression)
   {
      super(lineNum);

      this.expression = expression;
   }
}
