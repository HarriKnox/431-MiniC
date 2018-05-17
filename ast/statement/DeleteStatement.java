package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.type.StructType;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMFree;

import llvm.instruction.targeted.LLVMBitcast;

import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;
import llvm.type.LLVMStructType;

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
         ErrorPrinter.badDelete(this.expression.token, value.type.astString());
      
      
      if (!(value instanceof LLVMRegister))
      {
         ErrorPrinter.IDK(
               "DeleteStatement.buildLLVM:58",
               value.getClass().getName());
         return node;
      }
      
      
      LLVMBitcast bitcast = new LLVMBitcast(
            (LLVMRegister)value,
            new LLVMPointerType(new LLVMByteType()));
      
      LLVMFree free = new LLVMFree(bitcast.target);
      
      
      return node.add(bitcast).add(free);
   }
}
