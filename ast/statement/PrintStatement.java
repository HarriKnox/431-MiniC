package ast.statement;


import ast.expression.Expression;

public class PrintStatement
   extends Statement
{
   public final Expression expression;

   public PrintStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
