package ast.expression;


import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMLoad;
import llvm.instruction.LLVMScanf;

import llvm.value.variable.LLVMRegister;


public class ReadExpression extends Expression
{
   public ReadExpression(int lineNum)
   {
      super(lineNum, 0);
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMScanf scanf = new LLVMScanf();
      LLVMLoad load = new LLVMLoad(scanf.target)
      
      node.add(scanf).add(load);
      
      return scanf.target;
   }
}
