package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.instruction.targeted.comparison.LLVMsge;

import llvm.value.operand.LLVMOperand;


public class GreaterEqualsExpression extends RelationalExpression
{
   public GreaterEqualsExpression(
         Token token,
         Expression left,
         Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "greater-equals";
   }
   
   
   @Override
   protected LLVMTargetedInstruction getInstruction(
         LLVMOperand left, LLVMOperand right)
   {
      return new LLVMsge(left, right);
   }
}
