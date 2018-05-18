package ast.expression.binary.equality;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMeq;

import llvm.value.operand.LLVMOperand;


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
         LLVMOperand left, LLVMOperand right)
   {
      return new LLVMeq(left, right);
   }
}
