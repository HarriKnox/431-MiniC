package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;
import llvm.instruction.LLVMNegate;

import llvm.type.LLVMIntType;
import llvm.type.LLVMType;

import llvm.value.LLVMValue;


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
