package ast.statement;


import ast.expression.Expression;


public class PrintStatement extends Statement
{
   public final Expression expression;
   public final boolean println;


   public PrintStatement(int lineNum, Expression expression, boolean println)
   {
      super(lineNum);

      this.expression = expression;
      this.println = println;
   }
}
