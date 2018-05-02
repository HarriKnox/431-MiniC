package ast.expression.binary.equality;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.comparison.LLVMne;

import llvm.value.LLVMValue;


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
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMne(l, r);
   }
}
