package ast.expression.binary.relational;


import ast.expression.Expression;


public class LessEqualsExpression extends RelationalExpression
{
   public LessEqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "less-equals"
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMLessEquals(l, r);
   }
}
