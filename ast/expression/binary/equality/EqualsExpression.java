package ast.expression.binary.equality;


import ast.expression.Expression;


public class EqualsExpression extends EqualityExpression
{
   public EqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "equals";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMEquals(l, r);
   }
}
