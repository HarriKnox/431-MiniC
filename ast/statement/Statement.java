package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;
import ast.TokenedElement;


public abstract class Statement extends TokenedElement
{
   public Statement(Token token)
   {
      super(token);
   }
   
   
   public abstract LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit);
}
