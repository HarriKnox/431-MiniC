package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMPrintf;

import llvm.type.LLVMIntType;

import llvm.value.operand.LLVMOperand;


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
   public LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMOperand value = this.expression.buildLLVM(
            program, current, opts, node);
      
      
      if (value == null)
         return node;
      
      
      if (!(value.type instanceof LLVMIntType))
         ErrorPrinter.badPrint(this.expression.token, value.type.astString());
      
      
      LLVMPrintf printf = new LLVMPrintf(value, this.println);
      
      return node.add(printf);
   }
}
