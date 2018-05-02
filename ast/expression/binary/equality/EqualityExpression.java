package ast.expression.binary.equality;


import org.antlr.v4.runtime.Token;

import ast.expression.Expression;

import ast.expression.binary.BinaryExpression;

import llvm.value.LLVMValue;


public abstract class EqualityExpression extends BinaryExpression
{
   public EqualityExpression(Token token, Expression left, Expression right)
   {
      super(token, left, right);
   }
   
   
   @Override
   protected boolean areValidTypes(LLVMValue left, LLVMValue right)
   {
      return left.type.equivalent(right.type);
   }
}
