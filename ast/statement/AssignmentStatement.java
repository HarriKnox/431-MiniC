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
      LLVMVariable llvmTarget;
      LLVMOperand llvmSource;
      
      
      if (this.target.height >= this.source.height)
      {
         llvmTarget = this.target.buildLLVM(program, current, node);
         llvmSource = this.source.buildLLVM(program, current, node);
      }
      else
      {
         llvmSource = this.source.buildLLVM(program, current, node);
         llvmTarget = this.target.buildLLVM(program, current, node);
      }
      
      
      if (llvmTarget == null || llvmSource == null)
         return node;
      
      
      if (!(llvmTarget.type.equivalent(llvmSource.type)))
         ErrorPrinter.assignMistype(
               this.source.token,
               llvmTarget.type.astString(),
               llvmSource.type.astString());
      
      if (llvmSource instanceof LLVMNull)
         llvmSource = new LLVMNull(llvmTarget.type);
      
      
      LLVMStore store = new LLVMStore(llvmTarget, llvmSource);
      
      return node.add(store);
   }
}
