package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.type.LLVMBoolType;

import llvm.value.LLVMValue;


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
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMCFGNode guardNode = new LLVMCFGNode();
      
      LLVMValue llvmGuard = this.guard.buildLLVM(program, current, guardNode);
      
      node.jump(guardNode);
      
      
      if ((llvmGuard != null)
            && (!(llvmGuard.type instanceof LLVMBoolType)))
         ErrorPrinter.badGuard(this.guard.token, llvmGuard.type.astString());
      
      
      LLVMCFGNode bodyNode = new LLVMCFGNode();
      LLVMCFGNode elseNode = new LLVMCFGNode();
      
      guardNode.branch(llvmGuard, bodyNode, elseNode);
      
      
      LLVMCFGNode bodyLast = this.body.buildLLVM(
            program, current, bodyNode, exit);
      
      
      bodyLast.loopback(guardNode);
      
      
      return elseNode;
   }
}
