package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.type.LLVMVoidType;

import llvm.value.LLVMValue;


public class VoidExpression extends Expression
{
   public VoidExpression(Token token)
   {
      super(token, 0);
   }
   
   
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMRegister(new LLVMVoidType());
   }
}
