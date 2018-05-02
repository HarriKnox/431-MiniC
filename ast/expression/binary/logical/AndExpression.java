package ast.expression.binary.logical;


import ast.expression.Expression;


public class AndExpression extends LogicalExpression
{
   public AndExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "logical-and";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMAnd(l, r);
   }
}
