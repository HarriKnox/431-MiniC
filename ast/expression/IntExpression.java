package ast.expression;


import ast.ProgramAST;

import ast.declaration.Function;

import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;

import llvm.value.constant.LLVMInteger;


public class IntExpression extends Expression
{
   public final String value;
   public final boolean negative;


   public IntExpression(int lineNum, String value)
   {
      this(lineNum, value, false);
   }
   
   
   private IntExpression(int lineNum, String value, boolean negative)
   {
      super(lineNum, 0);

      this.value = value;
      this.negative = negative;
   }
   
   
   public IntExpression negate()
   {
      return new IntExpression(this.lineNum, this.value, !this.negative);
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
         System.err.println("line " + this.lineNum + " integer out of range");
         return null;
      }
      
      
      return new LLVMInt(val);
   }
}
