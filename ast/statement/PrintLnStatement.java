package ast.statement;

import ast.expression.Expression;

public class PrintLnStatement
   extends Statement
{
   public final Expression expression;

   public PrintLnStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
