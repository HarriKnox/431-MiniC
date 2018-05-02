package ast.statement;


import ast.expression.Expression;


public class WhileStatement extends Statement
{
   public final Expression guard;
   public final Statement body;


   public WhileStatement(int lineNum, Expression guard, Statement body)
   {
      super(lineNum);

      this.guard = guard;
      this.body = body;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMCFGNode guardNode = new LLVMCFGNode();
      
      LLVMValue llvmGuard = this.guard.buildLLVM(program, current, guardNode);
      
      node.jump(guardNode);
      
      
      if ((llvmGuard != null)
         && (!(llvmGuard.type instanceof LLVMBoolType)))
      {
         System.err.println("line " + conditional.line + " guard is of type " + g);
      }
      
      
      LLVMCFGNode bodyNode = new LLVMCFGNode();
      LLVMCFGNode elseNode = new LLVMCFGNode();
      
      guardNode.branch(llvmGuard.target, bodyNode, elsenode);
      
      
      LLVMCFGNode bodyLast = this.body.buildLLVM(
            program, current, bodyNode, exit);
      
      
      if (bodyLast != null)
         bodyLast.loopback(node);
      
      
      return elseNode;
   }
}
