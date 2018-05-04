package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;
import llvm.instruction.LLVMNot;

import llvm.type.LLVMBoolType;
import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public class NotExpression extends UnaryExpression
{
   public NotExpression(Token token, Expression operand)
   {
      super(token, operand);
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
