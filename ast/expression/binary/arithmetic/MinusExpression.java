package ast.expression.binary.arithmetic;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.arithmetic.LLVMsub;

import llvm.value.LLVMValue;


public class MinusExpression extends ArithmeticExpression
{
   public MinusExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "minus";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMValue left, LLVMValue right)
   {
      return new LLVMsub(left, right);
   }
}
