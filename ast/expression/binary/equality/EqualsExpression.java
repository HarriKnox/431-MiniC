package ast.expression.binary.equality;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMeq;

import llvm.value.LLVMValue;


public class EqualsExpression extends EqualityExpression
{
   public EqualsExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "equals";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMValue left, LLVMValue right)
   {
      return new LLVMeq(left, right);
   }
}
