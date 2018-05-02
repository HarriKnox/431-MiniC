package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMNull;


public class NullExpression extends Expression
{
   public NullExpression(Token token)
   {
      super(token, 0);
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMNull();
   }
}
