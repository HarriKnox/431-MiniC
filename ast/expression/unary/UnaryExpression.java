package ast.expression.unary;


import ast.expression.Expression;


public abstract class UnaryExpression extends Expression
{
   public final Expression operand;


   public UnaryExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand.height + 1);

      this.operand = operand;
   }
}
