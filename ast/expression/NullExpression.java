package ast.expression;


import ast.ProgramAST;

import llvm.LLVMCFGNode;

import llvm.value.LLVMNull;
import llvm.value.LLVMValue;


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
