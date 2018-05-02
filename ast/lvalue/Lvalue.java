package ast.lvalue;


import ast.LinedElement;
import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;


public abstract class Lvalue extends LinedElement
{
   /**
    * height is the distance to the bottom of the tree. 0 is a leaf.
    */
   public final int height;
   
   
   public Lvalue(int lineNum, int height)
   {
      super(lineNum);
      
      this.height = height;
   }
   
   
   public abstract LLVMVariable buildLLVM(
         ProgramAST program, Fuction current, LLVMCFGNode node);
}
