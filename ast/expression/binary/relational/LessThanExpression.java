package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMslt;

import llvm.value.operand.LLVMOperand;


public class LessThanExpression extends RelationalExpression
{
   public LessThanExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "less-than";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMOperand left, LLVMOperand right)
   {
      return new LLVMslt(left, right);
   }
}
