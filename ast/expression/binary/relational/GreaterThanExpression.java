package ast.expression.binary.relational;


import ast.expression.Expression;


public class GreaterThanExpression extends RelationalExpression
{
   public GreaterThanExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "greater-than";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMGreaterThan(l, r);
   }
}
