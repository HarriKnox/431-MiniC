package ast.expression.unary;


import ast.expression.Expression;


public class NotExpression extends UnaryExpression
{
   public NotExpression(int lineNum, Expression operand)
   {
      super(lineNum, operand);
   }
   
   
   @Override
   protected boolean isValidType(LLVMType type)
   {
      return type instanceof LLVMBoolType;
   }
   
   
   @Override
   protected String getOperation()
   {
      return "unary-not";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue value)
   {
      return new LLVMNot(value);
   }
}
