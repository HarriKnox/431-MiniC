package ast.expression;


import ast.LinedElement;


public abstract class Expression extends LinedElement
{
   public Expression(int lineNum)
   {
      super(lineNum);
   }
}
