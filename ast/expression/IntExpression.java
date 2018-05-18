package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.constant.LLVMInt;


public class IntExpression extends Expression
{
   public final String value;
   public final boolean negative;


   public IntExpression(Token token, String value)
   {
      this(token, value, false);
   }
   
   
   private IntExpression(Token token, String value, boolean negative)
   {
      super(token, 0);

      this.value = value;
      this.negative = negative;
   }
   
   
   public IntExpression negate()
   {
      return new IntExpression(this.token, this.value, !this.negative);
   }
   
   
   @Override
   public LLVMOperand buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      String val = this.negative ? ('-' + this.value) : this.value;
      int num;
      
      try
      {
         num = Integer.parseInt(val);
      }
      catch (NumberFormatException ne)
      {
         ErrorPrinter.printLine(this.token, "integer out of range");
         return null;
      }
      
      
      return new LLVMInt(num);
   }
}
