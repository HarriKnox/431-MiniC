package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMsle;

import llvm.value.LLVMValue;


public class LessEqualsExpression extends RelationalExpression
{
   public LessEqualsExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "less-equals";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMValue left, LLVMValue right)
   {
      return new LLVMsle(left, right);
   }
}
