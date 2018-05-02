package ast.expression.binary.relational;


import ast.expression.Expression;


public class LessThanExpression extends RelationalExpression
{
   public LessThanExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "less-than"
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMLessThan(l, r);
   }
}
