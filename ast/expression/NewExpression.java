package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Struct;

import ast.type.StructType;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMBitcast;
import llvm.instruction.LLVMMalloc;

import llvm.value.LLVMValue;


public class NewExpression extends Expression
{
   public final String id;


   public NewExpression(Token token, String id)
   {
      super(token, 0);
      this.id = id;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      Struct struct = program.structs.getStruct(this.id);
      
      if (struct == null)
      {
         Error.unknownStruct(this.token, this.id);
         return null;
      }
      
      LLVMMalloc malloc = new LLVMMalloc(struct.fields.length);
      LLVMBitcast bitcast = new LLVMBitcast(
            malloc.target,
            new StructType(struct.name).getLLVMType());
      
      node.add(malloc).add(bitcast);
      
      return bitcast.target;
   }
}
