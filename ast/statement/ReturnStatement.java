package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.type.LLVMVoidType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMReturnValue;


public class ReturnStatement extends Statement
{
   public final Expression expression;


   public ReturnStatement(Token token, Expression expression)
   {
      super(token);

      this.expression = expression;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      LLVMType retType = current.type.getLLVMType();
      
      
      if (value != null)
      {
         if (!value.type.equivalent(retType)))
            Error.badReturn(
                  this.expression.token,
                  retType.astString(),
                  value.type.astString());
         
         
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
