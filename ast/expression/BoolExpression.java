package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMBool;


public class BoolExpression extends Expression
{
   public final boolean value;


   public BoolExpression(Token token, boolean value)
   {
      super(token, 0);

      this.value = value;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMBool(this.value);
   }
}
