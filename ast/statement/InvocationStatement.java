package ast.statement;


import ast.expression.Expression;


public class InvocationStatement extends Statement
{
   public final Expression expression;


   public InvocationStatement(int lineNum, Expression expression)
   {
      super(lineNum);

      this.expression = expression;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      this.expression.buildLLVM(program, current, node);
      
      return node;
   }
}
