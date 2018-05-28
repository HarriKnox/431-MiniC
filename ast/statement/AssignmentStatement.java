package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.lvalue.Lvalue;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.constant.LLVMNull;

import llvm.value.variable.LLVMVariable;


public class AssignmentStatement extends Statement
{
   public final Lvalue target;
   public final Expression source;


   public AssignmentStatement(Token token, Lvalue target, Expression source)
   {
      super(token);

      this.target = target;
      this.source = source;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMVariable target;
      LLVMOperand value;
      
      
      if (target.height >= value.height)
      {
         target = this.target.buildLLVM(program, current, node);
         value = this.source.buildLLVM(program, current, node);
      }
      else
      {
         value = this.source.buildLLVM(program, current, node);
         target = this.target.buildLLVM(program, current, node);
      }
      
      
      if (target == null || value == null)
         return node;
      
      
      if (!(target.type.equivalent(value.type)))
         ErrorPrinter.assignMistype(
               this.source.token,
               target.type.astString(),
               value.type.astString());
      
      if (value instanceof LLVMNull)
         value = new LLVMNull(target.type);
      
      
      LLVMStore store = new LLVMStore(target, value);
      
      return node.add(store);
   }
}
