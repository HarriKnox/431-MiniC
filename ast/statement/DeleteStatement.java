package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.type.StructType;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMBitcast;
import llvm.instruction.LLVMFree;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class DeleteStatement extends Statement
{
   public final Expression expression;


   public DeleteStatement(Token token, Expression expression)
   {
      super(token);

      this.expression = expression;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      
      if (value == null)
         return node;
      
      
      if (!(value.type instanceof LLVMStructType))
         Error.badDelete(this.expression.token, value.type.astString());
      
      
      if (!(value instanceof LLVMRegister))
      {
         Error.IDK("DeleteStatement.buildLLVM:55", value.getClass().getName());
         return node;
      }
      
      
      LLVMBitcast bitcast = new LLVMBitcast(
            (LLVMRegister)value,
            new LLVMPointerType());
      
      LLVMFree free = new LLVMFree(bitcast.target);
      
      
      return node.add(bitcast).add(free);
   }
}
