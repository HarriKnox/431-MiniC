package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.type.LLVMVoidType;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.register.LLVMVirtual;


public class VoidExpression extends Expression
{
   public VoidExpression(Token token)
   {
      super(token, 0);
   }
   
   
   @Override
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      return new LLVMVirtual(new LLVMVoidType());
   }
}
