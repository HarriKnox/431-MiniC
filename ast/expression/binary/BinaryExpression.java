package ast.expression.binary;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


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
   
   
   @Override
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      LLVMOperand llvmLeft, llvmRight;
      
      
      if (this.left.height >= this.right.height)
      {
         llvmLeft = this.left.buildLLVM(program, current, opts, node);
         llvmRight = this.right.buildLLVM(program, current, opts, node);
      }
      else
      {
         llvmRight = this.right.buildLLVM(program, current, opts, node);
         llvmLeft = this.left.buildLLVM(program, current, opts, node);
      }
      
      
      if (llvmLeft == null || llvmRight == null)
         return null;
      
      
      if (!this.areValidTypes(llvmLeft, llvmRight))
      {
         ErrorPrinter.binaryMistype(
               this.token,
               this.getOperation(),
               llvmLeft.type.astString(),
               llvmRight.type.astString());
         
         return null;
      }
      
      
      LLVMTargetedInstruction instruction =
            this.getInstruction(llvmLeft, llvmRight);
      
      node.add(instruction);
      
      return instruction.target;
   }
   
   
   protected abstract boolean areValidTypes(
         LLVMOperand left, LLVMOperand right);
   
   protected abstract String getOperation();
   
   protected abstract LLVMTargetedInstruction getInstruction(
         LLVMOperand left, LLVMOperand right);
}
