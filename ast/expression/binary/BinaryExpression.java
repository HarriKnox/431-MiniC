package ast.expression.binary;


import ast.expression.Expression;

import llvm.type.Type;

import llvm.value.LLVMValue;


public abstract class BinaryExpression extends Expression
{
   public final Expression left;
   public final Expression right;


   public BinaryExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, ((left.height > right.height)
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
         System.err.println("line " + this.line + " attempt to perform "
               + this.getOperation() + " on " + l + " and " + r);
         return null;
      }
      
      
      LLVMInstruction instruction = this.getInstruction(left, right);
      
      node.add(instruction;
      
      return instruction.target;
   }
   
   
   protected abstract boolean areValidTypes(LLVMValue left, LLVMValue right);
   
   protected abstract String getOperation();
   
   protected abstract LLVMInstruction getInstruction(LLVMValue l, LLVMValue r);
}
