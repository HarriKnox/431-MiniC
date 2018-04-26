package ast.statement;

import ast.LinedElement;

public abstract class Statement
   extends LinedElement
{
   public Statement(int lineNum)
   {
      super(lineNum);
   }
}
