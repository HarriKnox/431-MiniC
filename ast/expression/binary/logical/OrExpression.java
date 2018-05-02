package ast.expression.binary.logical;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.logical.LLVMor;

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
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMor(l, r);
   }
}
