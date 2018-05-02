package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.lvalue.Lvalue;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.value.LLVMValue;

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
      LLVMVariable target = this.target.buildLLVM(program, current, node);
      LLVMValue value = this.source.buildLLVM(program, current, node);
      
      
      if (target == null || value == null)
         return node;
      
      
      if (!(target.type.equivalent(value.type)))
         Error.assignMistype(
               this.source.token,
               this.target.type.astString(),
               this.value.type.astString());
      
      
      LLVMStore store = new LLVMStore(target, value);
      
      return node.add(store);
   }
}
