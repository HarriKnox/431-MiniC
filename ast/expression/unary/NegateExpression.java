package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMNegate;
import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMIntType;
import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


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
   protected LLVMTargetedInstruction getInstruction(LLVMOperand value)
   {
      return new LLVMNegate(value);
   }
}
