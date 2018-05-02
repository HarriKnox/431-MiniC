package ast.expression.binary.logical;


import ast.expression.Expression;


public class OrExpression extends LogicalExpression
{
   public OrExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "logical-or";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMOr(l, r);
   }
}
