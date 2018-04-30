package ast.expression;


import ast.LinedElement;


public abstract class Expression extends LinedElement
{
   public final int height;
   
   
   public Expression(int lineNum, int height)
   {
      super(lineNum);
      
      this.height = height;
   }
}
