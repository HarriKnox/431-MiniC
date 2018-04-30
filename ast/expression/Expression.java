package ast.expression;


import ast.LinedElement;
import ast.ProgramAST;

import llvm.value.LLVMValue;
import llvm.LLVMCFGNode;


public abstract class Expression extends LinedElement
{
   public final int height;
   
   
   public Expression(int lineNum, int height)
   {
      super(lineNum);
      
      this.height = height;
   }
   
   
   public abstract LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node);
}
