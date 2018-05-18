package ast.expression.binary.logical;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.type.LLVMBoolType;

import llvm.value.operand.LLVMOperand;


public abstract class LogicalExpression extends BinaryExpression
{
   public LogicalExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMOperand left, LLVMOperand right)
   {
      return (left.type instanceof LLVMBoolType)
            && (right.type instanceof LLVMBoolType);
   }
}
