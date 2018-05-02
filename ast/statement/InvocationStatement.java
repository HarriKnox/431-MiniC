package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import llvm.LLVMCFGNode;


public class InvocationStatement extends Statement
{
   public final Expression expression;


   public InvocationStatement(Token token, Expression expression)
   {
      super(token);

      this.expression = expression;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      this.expression.buildLLVM(program, current, node);
      
      return node;
   }
}
