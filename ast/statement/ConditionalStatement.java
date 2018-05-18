package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.value.operand.LLVMOperand;

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
      LLVMOperand llvmGuard = this.guard.buildLLVM(program, current, node);
      
      
      if ((llvmGuard != null)
            && (!(llvmGuard.type instanceof LLVMBoolType)))
         ErrorPrinter.badGuard(this.guard.token, llvmGuard.type.astString());
      
      
      LLVMCFGNode thenNode = new LLVMCFGNode(false);
      LLVMCFGNode elseNode = new LLVMCFGNode(false);
      
      node.branch(llvmGuard, thenNode, elseNode);
      
      
      LLVMCFGNode thenLast = this.thenBlock.buildLLVM(
            program, current, thenNode, exit);
      
      LLVMCFGNode elseLast = this.elseBlock.buildLLVM(
            program, current, elseNode, exit);
      
      
      /*
       * Add the join node. If the join is unreachable, construct an
       * unreachable node but still add the jumps. It'll all work out in the
       * end.
       */
      LLVMCFGNode join = new LLVMCFGNode(
            thenLast.unreachable && elseLast.unreachable);
      
      thenLast.jump(join);
      elseLast.jump(join);
      
      
      return join;
   }
}
