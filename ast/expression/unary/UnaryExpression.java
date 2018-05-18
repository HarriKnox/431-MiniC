package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMTargetedInstruction;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


public abstract class UnaryExpression extends Expression
{
   public final Expression operand;


   public UnaryExpression(Token token, Expression operand)
   {
      super(token, operand.height + 1);

      this.operand = operand;
   }
   
   
   @Override
   public LLVMOperand buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMOperand value = this.operand.buildLLVM(program, current, node);
      
      
      if (value == null)
         return null;
      
      
      if (!this.isValidType(value.type))
      {
         ErrorPrinter.unaryMistype(
               this.token,
               this.getOperation(),
               value.type.astString());
         
         return null;
      }
      
      
      LLVMTargetedInstruction instruction = this.getInstruction(value);
      
      node.add(instruction);
      
      return instruction.target;
   }
   
   
   protected abstract boolean isValidType(LLVMType type);
   
   protected abstract String getOperation();
   
   protected abstract LLVMTargetedInstruction
         getInstruction(LLVMOperand value);
}
