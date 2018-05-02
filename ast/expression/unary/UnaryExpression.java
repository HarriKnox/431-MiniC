package ast.expression.unary;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import llvm.LLVMCFGNode;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public abstract class UnaryExpression extends Expression
{
   public final Expression operand;


   public UnaryExpression(Token token, Expression operand)
   {
      super(token, operand.height + 1);

      this.operand = operand;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMValue value = this.operand.buildLLVM(program, current, node);
      
      
      if (value == null)
         return null;
      
      
      if (!this.isValidType(value.type))
      {
         System.err.println("line " + this.line + " attempt to perform "
            + this.getOperation() + " on " + o);
         return null;
      }
      
      
      LLVMInstruction instruction = this.getInstruction(value);
      
      node.add(instruction);
      
      return instruction.target;
   }
   
   
   protected abstract boolean isValidType(LLVMType type);
   
   protected abstract String getOperation();
   
   protected abstract LLVMInstruction getInstruction(LLVMValue value);
}
