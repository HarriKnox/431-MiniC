package ast.expression;


import ast.ProgramAST;

import llvm.LLVMCFGNode;

import llvm.value.LLVMBool;
import llvm.value.LLVMValue;


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
