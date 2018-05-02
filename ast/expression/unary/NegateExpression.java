package ast.expression.unary;


import ast.expression.Expression;


public class NegateExpression extends UnaryExpression
{
   public NegateExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
   
   
   @Override
   protected boolean isValidType(LLVMType type)
   {
      return type instanceof LLVMIntType;
   }
   
   
   @Override
   protected String getOperation()
   {
      return "unary-minus";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue value)
   {
      return new LLVMNegate(value);
   }
}
