package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.type.LLVMBoolType;

import llvm.value.operand.LLVMOperand;


public class WhileStatement extends Statement
{
   public final Expression guard;
   public final Statement body;


   public WhileStatement(Token token, Expression guard, Statement body)
   {
      super(token);

      this.guard = guard;
      this.body = body;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMCFGNode guardNode = new LLVMCFGNode(false);
      
      guardNode.sealed = false;
      
      
      node.jump(guardNode);
      
      LLVMOperand llvmGuard = this.guard.buildLLVM(
            program, current, opts, guardNode);
      
      
      if ((llvmGuard != null)
            && (!(llvmGuard.type instanceof LLVMBoolType)))
         ErrorPrinter.badGuard(this.guard.token, llvmGuard.type.astString());
      
      
      LLVMCFGNode bodyNode = new LLVMCFGNode(false);
      LLVMCFGNode elseNode = new LLVMCFGNode(false);
      
      guardNode.branch(llvmGuard, bodyNode, elseNode);
      
      
      LLVMCFGNode bodyLast = this.body.buildLLVM(
            program, current, opts, bodyNode, exit);
      
      
      bodyLast.loopback(guardNode);
      
      guardNode.sealed = true;
      
      
      return elseNode;
   }
}
