package ast.expression.binary.arithmetic;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.type.LLVMIntType;

import llvm.value.operand.LLVMOperand;


public abstract class ArithmeticExpression extends BinaryExpression
{
   public ArithmeticExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMOperand left, LLVMOperand right)
   {
      return (left.type instanceof LLVMIntType)
            && (right.type instanceof LLVMIntType);
   }
}
