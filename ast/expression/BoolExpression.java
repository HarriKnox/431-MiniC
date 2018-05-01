package ast.expression;


import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMBool;


public class BoolExpression extends Expression
{
   public final boolean value;


   public BoolExpression(int lineNum, boolean value)
   {
      super(lineNum, 0);

      this.value = value;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      return new LLVMBool(this.value);
   }
}
