package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMInt;


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
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      String val = this.negative ? ("-" + this.value) : this.value;
      
      
      try
      {
         Integer.parseInt(val);
      }
      catch (NumberFormatException ne)
      {
         ErrorPrinter.printLine(this.token, "integer out of range");
         return null;
      }
      
      
      return new LLVMInt(val);
   }
}
