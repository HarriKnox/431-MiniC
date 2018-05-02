package ast.lvalue;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.variable.LLVMVariable;


public abstract class Lvalue extends TokenedElement
{
   /**
    * height is the distance to the bottom of the tree. 0 is a leaf.
    */
   public final int height;
   
   
   public Lvalue(Token token, int height)
   {
      super(token);
      
      this.height = height;
   }
   
   
   public abstract LLVMVariable buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node);
}
