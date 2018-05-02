package ast.statement;


import ast.expression.Expression;

import ast.type.StructType;


public class DeleteStatement extends Statement
{
   public final Expression expression;


   public DeleteStatement(int lineNum, Expression expression)
   {
      super(lineNum);

      this.expression = expression;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      
      if (value == null)
         return node;
      
      
      if (!(value.type instanceof LLVMStructType))
      {
         System.err.println("line " + delete.line + " cannot delete values of type " + s);
      }
      
      
      if (!(value instanceof LLVMRegister))
      {
         System.err.println("I have no idea what went wrong because this should never run.");
         return node;
      }
      
      
      LLVMBitcast bitcast = new LLVMBitcast(
            (LLVMRegister)value,
            new LLVMPointerType());
      
      LLVMFree free = new LLVMFree(bitcast.target);
      
      
      return node.add(bitcast).add(free);
   }
}
