package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;


public abstract class Expression extends TokenedElement
{
   public final int height;
   
   
   public Expression(Token token, int height)
   {
      super(token);
      
      this.height = height;
   }
   
   
   public abstract LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node);
}
