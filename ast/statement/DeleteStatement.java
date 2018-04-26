package ast.statement;

import ast.expression.Expression;
import ast.type.StructType;

public class DeleteStatement
   extends Statement
{
   public final Expression expression;
   public StructType type;

   public DeleteStatement(int lineNum, Expression expression)
   {
      super(lineNum);
      this.expression = expression;
   }
}
