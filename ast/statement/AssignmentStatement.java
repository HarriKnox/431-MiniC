package ast.statement;


import ast.expression.Expression;

import ast.lvalue.Lvalue;


public class AssignmentStatement extends Statement
{
   public final Lvalue target;
   public final Expression source;


   public AssignmentStatement(int lineNum, Lvalue target, Expression source)
   {
      super(lineNum);

      this.target = target;
      this.source = source;
   }
}
