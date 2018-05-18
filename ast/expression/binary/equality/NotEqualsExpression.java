package ast.expression.binary.equality;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMne;

import llvm.value.operand.LLVMOperand;


public class NotEqualsExpression extends EqualityExpression
{
   public NotEqualsExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "not-equals";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMOperand left, LLVMOperand right)
   {
      return new LLVMne(left, right);
   }
}
