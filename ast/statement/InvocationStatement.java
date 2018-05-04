package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.InvocationExpression;

import llvm.LLVMCFGNode;


public class InvocationStatement extends Statement
{
   public final InvocationExpression invocation;


   public InvocationStatement(Token token, InvocationExpression invocation)
   {
      super(token);

      this.invocation = invocation;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      this.invocation.buildLLVM(program, current, node);
      
      return node;
   }
}
