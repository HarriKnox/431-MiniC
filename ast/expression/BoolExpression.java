package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.constant.LLVMBool;


public class BoolExpression extends Expression
{
   public final boolean value;


   public BoolExpression(Token token, boolean value)
   {
      super(token, 0);

      this.value = value;
   }
   
   
   @Override
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      return new LLVMBool(this.value);
   }
}
