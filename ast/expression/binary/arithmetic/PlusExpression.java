package ast.expression.binary.arithmetic;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.arithmetic.LLVMadd;

import llvm.value.LLVMValue;


public class PlusExpression extends ArithmeticExpression
{
   public PlusExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "plus";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMadd(l, r);
   }
}
