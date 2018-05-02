package ast.expression.binary.arithmetic;


import ast.expression.Expression;


public class MinusExpression extends ArithmeticExpression
{
   public MinusExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "minus";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMMinus(l, r);
   }
}
