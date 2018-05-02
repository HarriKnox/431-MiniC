package ast.statement;


import java.util.LinkedList;
import java.util.List;


public class BlockStatement extends Statement
{
   public final List<Statement> statements;


   public BlockStatement(int lineNum, List<Statement> statements)
   {
      super(lineNum);

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
            System.err.println("line " + statement.lineNul
                  + " WARNING: code after a return will not be examined nor executed");
            break;
         }
         
         node = statement.buildLLVM(program, current, node, exit);
      }
      
      return node;
   }
}
