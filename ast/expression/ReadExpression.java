package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMScanf;

import llvm.instruction.targeted.LLVMLoad;

import llvm.value.operand.LLVMOperand;


import static llvm.value.variable.LLVMGlobal.SCANF_SCRATCH;


public class ReadExpression extends Expression
{
   public ReadExpression(Token token)
   {
      super(token, 0);
   }
   
   
   @Override
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      LLVMScanf scanf = new LLVMScanf();
      LLVMLoad load = new LLVMLoad(SCANF_SCRATCH);
      
      node.add(scanf).add(load);
      
      return load.target;
   }
}
