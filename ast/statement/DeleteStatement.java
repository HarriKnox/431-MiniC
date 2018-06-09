package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.type.StructType;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMFree;

import llvm.instruction.targeted.LLVMBitcast;

import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;
import llvm.type.LLVMStructType;

import llvm.value.operand.LLVMOperand;


public class DeleteStatement extends Statement
{
   public final Expression expression;


   public DeleteStatement(Token token, Expression expression)
   {
      super(token);

      this.expression = expression;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMOperand value = this.expression.buildLLVM(
            program, current, opts, node);
      
      
      if (value == null)
         return node;
      
      
      if (!(value.type instanceof LLVMStructType))
         ErrorPrinter.badDelete(this.expression.token, value.type.astString());
      
      
      LLVMBitcast bitcast = new LLVMBitcast(
            value,
            new LLVMPointerType(new LLVMByteType()));
      
      LLVMFree free = new LLVMFree(bitcast.target);
      
      
      return node.add(bitcast).add(free);
   }
}
