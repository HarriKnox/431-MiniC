package ast.expression.binary.logical;


import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;


public abstract class LogicalExpression extends BinaryExpression
{
   public LogicalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMValue left, LLVMValue right)
   {
      return (left.type instanceof LLVMBoolType)
            && (right.type instanceof LLVMBoolType);
   }
}
