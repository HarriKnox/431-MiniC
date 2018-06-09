package ast.lvalue;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Variable;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMGetelementptr;

import llvm.instruction.targeted.LLVMLoad;

import llvm.type.LLVMStructType;

import llvm.value.variable.LLVMVariable;


public class LvalueDot extends Lvalue
{
   public final Lvalue left;
   public final String id;


   public LvalueDot(Token token, Lvalue left, String id)
   {
      super(token, left.height + 1);

      this.left = left;
      this.id = id;
   }
   
   
   @Override
   public LLVMVariable buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      LLVMVariable leftValue = this.left.buildLLVM(
            program, current, opts, node);
      
      
      /* null indicates something went wrong deeper in the tree */
      if (leftValue == null)
         return null;
      
      
      if (!(leftValue.type instanceof LLVMStructType))
      {
         ErrorPrinter.badIndex(this.token, leftValue.type.astString());
         return null;
      }
      
      
      /* At this point leftValue refers to a valid struct */
      Variable field = program
            .getStruct(((LLVMStructType)leftValue.type).name)
            .getField(this.id);
      
      if (field == null)
      {
         ErrorPrinter.noField(
               this.token,
               ((LLVMStructType)leftValue.type).name,
               this.id);
         
         return null;
      }
      
      
      LLVMLoad load = new LLVMLoad(leftValue);
      
      LLVMGetelementptr getelementptr = new LLVMGetelementptr(
            load.target,
            field.type.llvmType(),
            field.index);
      
      node.add(load).add(getelementptr);
      
      
      return getelementptr.target;
   }
}
