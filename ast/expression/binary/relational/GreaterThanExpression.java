package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.comparison.LLVMsgt;

import llvm.value.LLVMValue;


public class GreaterThanExpression extends RelationalExpression
{
   public GreaterThanExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "greater-than";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMsgt(l, r);
   }
}
