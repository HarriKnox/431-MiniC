package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.comparison.LLVMslt;

import llvm.value.LLVMValue;


public class LessThanExpression extends RelationalExpression
{
   public LessThanExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "less-than"
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMslt(l, r);
   }
}
