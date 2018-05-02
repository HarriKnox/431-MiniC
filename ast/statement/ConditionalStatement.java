package ast.statement;


import ast.expression.Expression;


public class ConditionalStatement extends Statement
{
   public final Expression guard;
   public final Statement thenBlock;
   public final Statement elseBlock;


   public ConditionalStatement(
         int lineNum,
         Expression guard,
         Statement thenBlock,
         Statement elseBlock)
   {
      super(lineNum);

      this.guard = guard;
      this.thenBlock = thenBlock;
      this.elseBlock = elseBlock;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue llvmGuard = this.guard.buildLLVM(program, current, node);
      
      
      if ((llvmGuard != null)
         && (!(llvmGuard.type instanceof LLVMBoolType)))
      {
         System.err.println("line " + conditional.line + " guard is of type " + g);
      }
      
      
      LLVMCFGNode thenNode = new LLVMCFGNode();
      LLVMCFGNode elseNode = new LLVMCFGNode();
      
      node.branch(llvmGuard.target, thenNode, elseNode);
      
      
      LLVMCFGNode thenLast = this.thenBlock.buildLLVM(
            program, current, thenNode, exit);
      
      LLVMCFGNode elseLast = this.elseBlock.buildLLVM(
            program, current, elseNode, exit);
      
      
      if (thenLast == null && elseLast == null)
         return null;
      
      
      LLVMCFGNode join = new LLVMCFGNode();
      
      if (thenLast != null)
         thenLast.jump(join);
      
      if (elseLast != null)
         elseLast.jump(join);
      
      
      return join;
   }
}
