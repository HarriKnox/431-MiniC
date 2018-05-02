package ast.statement;


import ast.expression.Expression;


public class PrintStatement extends Statement
{
   public final Expression expression;
   public final boolean println;


   public PrintStatement(int lineNum, Expression expression, boolean println)
   {
      super(lineNum);

      this.expression = expression;
      this.println = println;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      
      if (value == null)
         return null;
      
      
      if (!(value.type instanceof LLVMIntType))
      {
         System.err.println("line " + this.lineNum
               + " cannot print value of type " + q);
         ok = false;
      }
      
      
      LLVMPrint print = new LLVMPrint(value, this.println);
      
      return node.add(print);
   }
}
