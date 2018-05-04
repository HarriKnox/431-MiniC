package ast.expression.binary.logical;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.logical.LLVMor;

import llvm.value.LLVMValue;


public class OrExpression extends LogicalExpression
{
   public OrExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "logical-or";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMValue left, LLVMValue right)
   {
      return new LLVMor(left, right);
   }
}
