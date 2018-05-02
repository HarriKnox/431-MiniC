package ast.expression.binary.relational;


import ast.expression.Expression;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.comparison.LLVMsge;


public class GreaterEqualsExpression extends RelationalExpression
{
   public GreaterEqualsExpression(
         int lineNum,
         Expression left,
         Expression right)
   {
      super(lineNum, left, right);
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
