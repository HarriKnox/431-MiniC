package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.comparison.LLVMsge;

import llvm.value.LLVMValue;


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
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMsge(l, r);
   }
}
