package ast.statement;


import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.type.LLVMVoidType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMReturnValue;


public class ReturnStatement extends Statement
{
   public final Expression expression;


   public ReturnStatement(int lineNum, Expression expression)
   {
      super(lineNum);

      this.expression = expression;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      
      if (value != null)
      {
         if (!value.type.equivalent(current.type.getLLVMType())))
         {
            System.err.println("line " + this.lineNum + " cannot return "
                  + r + ", expected " + returnType);
         }
         
         
         if (!(value.type instanceof LLVMVoidType))
         {
            LLVMStore store = new LLVMStore(
                  new LLVMReturnValue(current.name, value.type),
                  value);
            
            node.add(store);
         }
      }
      
      
      node.jump(exit);
      
      
      /* `null` indicates a statement is return equivalent */
      return null;
   }
}
