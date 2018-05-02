package ast.expression.binary.arithmetic;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.arithmetic.LLVMmul;

import llvm.value.LLVMValue;


public class TimesExpression extends ArithmeticExpression
{
   public TimesExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "times";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMmul(l, r);
   }
}
