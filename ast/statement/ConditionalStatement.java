package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.type.LLVMBoolType;


public class ConditionalStatement extends Statement
{
   public final Expression guard;
   public final Statement thenBlock;
   public final Statement elseBlock;


   public ConditionalStatement(
         Token token,
         Expression guard,
         Statement thenBlock,
         Statement elseBlock)
   {
      super(token);

      this.guard = guard;
      this.thenBlock = thenBlock;
      this.elseBlock = elseBlock;
   }
   
   
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue llvmGuard = this.guard.buildLLVM(program, current, node);
      
      
      if ((llvmGuard != null)
         && (!(llvmGuard.type instanceof LLVMBoolType)))
      {
         System.err.println("line " + conditional.line + " guard is of type " + g);
      }
      
      
      LLVMCFGNode thenNode = new LLVMCFGNode();
      LLVMCFGNode elseNode = new LLVMCFGNode();
      
      node.branch(llvmGuard.target, thenNode, elseNode);
      
      
      LLVMCFGNode thenLast = this.thenBlock.buildLLVM(
            program, current, thenNode, exit);
      
      LLVMCFGNode elseLast = this.elseBlock.buildLLVM(
            program, current, elseNode, exit);
      
      
      if (thenLast == null && elseLast == null)
         return null;
      
      
      LLVMCFGNode join = new LLVMCFGNode();
      
      if (thenLast != null)
         thenLast.jump(join);
      
      if (elseLast != null)
         elseLast.jump(join);
      
      
      return join;
   }
}
