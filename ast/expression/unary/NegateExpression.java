package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;


public class NegateExpression extends UnaryExpression
{
   public NegateExpression(Token token, Expression operand)
   {
      super(token, operand);
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
