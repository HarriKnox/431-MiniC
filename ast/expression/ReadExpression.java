package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMScanf;

import llvm.instruction.targeted.LLVMLoad;

import llvm.value.LLVMValue;


import static llvm.value.variable.LLVMGlobal.SCANF_SCRATCH;


public class ReadExpression extends Expression
{
   public ReadExpression(Token token)
   {
      super(token, 0);
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMScanf scanf = new LLVMScanf();
      LLVMLoad load = new LLVMLoad(SCANF_SCRATCH);
      
      node.add(scanf).add(load);
      
      return load.target;
   }
}
