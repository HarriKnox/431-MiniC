package ast.expression.binary;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public abstract class BinaryExpression extends Expression
{
   public final Expression left;
   public final Expression right;


   public BinaryExpression(Token token, Expression left, Expression right)
   {
      super(token, ((left.height > right.height)
            ? left.height
            : right.height) + 1);

      this.left = left;
      this.right = right;
   }
   
   
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMValue left = this.left.buildLLVM(program, current, node);
      LLVMValue right = this.right.buildLLVM(program, current, node);
      
      
      if (left == null || right == null)
         return null;
      
      
      if (!this.areValidTypes(left, right))
      {
         ErrorPrinter.binaryMistype(
               this.token,
               this.getOperation(),
               left.type.astString(),
               right.type.astString());
         
         return null;
      }
      
      
      LLVMTargetedInstruction instruction = this.getInstruction(left, right);
      
      node.add(instruction);
      
      return instruction.target;
   }
   
   
   protected abstract boolean areValidTypes(LLVMValue left, LLVMValue right);
   
   protected abstract String getOperation();
   
   protected abstract LLVMTargetedInstruction getInstruction(
         LLVMValue left, LLVMValue right);
}
