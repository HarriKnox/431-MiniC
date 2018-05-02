package ast.expression.binary.relational;


import ast.expression.Expression;
import ast.expression.binary.BinaryExpression;


public abstract class RelationalExpression extends BinaryExpression
{
   public RelationalExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMValue left, LLVMValue right)
   {
      return (left.type instanceof LLVMIntType)
            && (right.type instanceof LLVMIntType);
   }
}
