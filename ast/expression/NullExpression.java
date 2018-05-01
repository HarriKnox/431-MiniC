package ast.expression;


import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMNull;


public class NullExpression extends Expression
{
   public NullExpression(int lineNum)
   {
      super(lineNum, 0);
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMNull();
   }
}
