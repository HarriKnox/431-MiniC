package ast.statement;


import ast.LinedElement;


public abstract class Statement extends LinedElement
{
   public Statement(int lineNum)
   {
      super(lineNum);
   }
   
   
   public abstract LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit);
}
