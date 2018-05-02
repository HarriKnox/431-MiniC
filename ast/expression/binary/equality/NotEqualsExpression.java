package ast.expression.binary.equality;


import ast.expression.Expression;


public class NotEqualsExpression extends EqualityExpression
{
   public NotEqualsExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "not-equals";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMNotEquals(l, r);
   }
}
