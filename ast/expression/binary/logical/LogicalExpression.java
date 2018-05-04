package ast.expression.binary.logical;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.type.LLVMBoolType;

import llvm.value.LLVMValue;


public abstract class LogicalExpression extends BinaryExpression
{
   public LogicalExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMValue left, LLVMValue right)
   {
      return (left.type instanceof LLVMBoolType)
            && (right.type instanceof LLVMBoolType);
   }
}
