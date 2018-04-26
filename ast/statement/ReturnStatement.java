package ast.statement;

import ast.expression.Expression;
import ast.type.Type;

public class ReturnStatement
   extends Statement
{
   public final Expression expression;
   public Type type;
   public String funcName;

   public ReturnStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
