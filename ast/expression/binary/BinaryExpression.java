package ast.expression.binary;


import ast.expression.Expression;


public abstract class BinaryExpression extends Expression
{
   public final Expression left;
   public final Expression right;


   public BinaryExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, ((left.height > right.height)
            ? left.height
            : right.height) + 1);

      this.left = left;
      this.right = right;
   }
}
