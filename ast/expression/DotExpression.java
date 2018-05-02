package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Variable;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMGetelementptr;
import llvm.instruction.LLVMLoad;

import llvm.type.LLVMStructType;

import llvm.value.LLVMValue;


public class DotExpression extends Expression
{
   public final Expression left;
   public final String id;


   public DotExpression(Token token, Expression left, String id)
   {
      super(token, left.height + 1);

      this.left = left;
      this.id = id;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMValue leftValue = this.left.buildLLVM(program, current, node);
      
      
      /* null indicates something went wrong deeper in the tree */
      if (leftValue == null)
         return null;
      
      
      if (!(leftValue.type instanceof LLVMStructType))
      {
         System.err.println("line " + exp.line + " attempt to index a(n) " + s);
         return null;
      }
      
      
      /* At this point leftValue refers to a valid struct */
      Variable field = program
            .getStruct(((LLVMStructType)leftValue.type).name)
            .getField(this.id);
      
      if (field == null)
      {
         System.err.println("line " + this.line + " struct "
               + ((LLVMStructType)leftValue.type).name
               + " does not contain field " + this.id);
         return null;
      }
      
      
      LLVMGetelementptr getelementptr = new LLVMGetelementptr(
            leftValue,
            field.type.getLLVMType()
            field.index);
      
      LLVMLoad load = new LLVMLoad(getelementptr.target);
      
      node.add(getelementptr).add(load);
      
      
      return load.target;
   }
}
