package ast.expression;

import ast.LinedElement;
import ast.type.Type;

public abstract class Expression
   extends LinedElement
{
   public Type type;
   
   public Expression(int lineNum)
   {
      super(lineNum);
   }
}
