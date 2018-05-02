package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMPrint;

import llvm.type.LLVMintType;

import llvm.value.LLVMValue;


public class PrintStatement extends Statement
{
   public final Expression expression;
   public final boolean println;


   public PrintStatement(Token token, Expression expression, boolean println)
   {
      super(token);

      this.expression = expression;
      this.println = println;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMValue value = this.expression.buildLLVM(program, current, node);
      
      
      if (value == null)
         return null;
      
      
      if (!(value.type instanceof LLVMIntType))
         Error.badPrint(this.expression.token, value.type.astString());
      
      
      LLVMPrint print = new LLVMPrint(value, this.println);
      
      return node.add(print);
   }
}
