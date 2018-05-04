package ast.expression.binary.arithmetic;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.value.LLVMValue;


public abstract class ArithmeticExpression extends BinaryExpression
{
   public ArithmeticExpression(Token token, Expression left, Expression right)
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
