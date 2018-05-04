package ast.expression.binary.relational;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.type.LLVMIntType;

import llvm.value.LLVMValue;


public abstract class RelationalExpression extends BinaryExpression
{
   public RelationalExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMValue left, LLVMValue right)
   {
      return (left.type instanceof LLVMIntType)
            && (right.type instanceof LLVMIntType);
   }
}
