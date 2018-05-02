package ast.expression.binary.arithmetic;


import ast.expression.Expression;


public class DivideExpression extends ArithmeticExpression
{
   public DivideExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "divide";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMDivide(l, r);
   }
}
