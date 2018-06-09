package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;
import ast.TokenedElement;

import ast.declaration.Function;

import common.Options;

import llvm.LLVMCFGNode;


public abstract class Statement extends TokenedElement
{
   public Statement(Token token)
   {
      super(token);
   }
   
   
   public abstract LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit);
}
