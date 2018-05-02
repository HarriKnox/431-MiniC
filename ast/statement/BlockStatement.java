package ast.statement;


import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;


public class BlockStatement extends Statement
{
   public final List<Statement> statements;


   public BlockStatement(Token token, List<Statement> statements)
   {
      super(token);

      this.statements = statements;
   }


   public boolean isEmpty()
   {
      return this.statements.isEmpty();
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      for (Statement statement : this.statements)
      {
         if (node == null)
         {
            System.err.println("line " + statement.token
                  + " WARNING: code after a return will not be examined nor executed");
            break;
         }
         
         node = statement.buildLLVM(program, current, node, exit);
      }
      
      return node;
   }
}
