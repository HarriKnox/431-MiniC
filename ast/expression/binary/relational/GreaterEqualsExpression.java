package ast.expression.binary.relational;


import ast.expression.Expression;


public class GreaterEqualsExpression extends RelationalExpression
{
   public GreaterEqualsExpression(
         int lineNum,
         Expression left,
         Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "greater-equals";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMGreaterEquals(l, r);
   }
}
