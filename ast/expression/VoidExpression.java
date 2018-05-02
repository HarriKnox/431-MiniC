package ast.expression;


import ast.LinedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.type.LLVMVoidType;

import llvm.value.LLVMValue;


public class VoidExpression extends Expression
{
   public VoidExpression(int lineNum)
   {
      super(lineNum, 0);
   }
   
   
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMRegister(new LLVMVoidType());
   }
}
