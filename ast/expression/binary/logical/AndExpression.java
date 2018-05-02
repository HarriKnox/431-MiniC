package ast.expression.binary.logical;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.logical.LLVMand;

import llvm.value.LLVMValue;


public class AndExpression extends LogicalExpression
{
   public AndExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected String getOperation()
   {
      return "logical-and";
   }
   
   
   @Override
   protected LLVMInstruction getInstruction(LLVMValue l, LLVMValue r)
   {
      return new LLVMand(l, r);
   }
}
