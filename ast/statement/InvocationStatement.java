package ast.statement;


import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.InvocationExpression;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMCallVoid;

import llvm.value.operand.LLVMOperand;


public class InvocationStatement extends Statement
{
   public final InvocationExpression invocation;
   
   
   public InvocationStatement(Token token, InvocationExpression invocation)
   {
      super(token);

      this.invocation = invocation;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit)
   {
      Function function = this.invocation.getFunction(program);
      
      if (function == null)
         return node;
      
      
      List<LLVMOperand> args = this.invocation.gatherArguments(
            function, program, current, opts, node);
      
      if (args == null)
         return node;
      
      
      LLVMCallVoid call = new LLVMCallVoid(
               function.name,
               function.type.llvmType(),
               args);
      
      return node.add(call);
   }
}
