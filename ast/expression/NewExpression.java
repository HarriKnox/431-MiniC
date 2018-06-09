package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Struct;

import ast.type.StructType;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMBitcast;
import llvm.instruction.targeted.LLVMMalloc;

import llvm.value.operand.LLVMOperand;


public class NewExpression extends Expression
{
   public final String id;


   public NewExpression(Token token, String id)
   {
      super(token, 0);
      this.id = id;
   }
   
   
   @Override
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      Struct struct = program.structs.getStruct(this.id);
      
      if (struct == null)
      {
         ErrorPrinter.unknownStruct(this.token, this.id);
         return null;
      }
      
      LLVMMalloc malloc = new LLVMMalloc(struct.fields.length);
      LLVMBitcast bitcast = new LLVMBitcast(
            malloc.target,
            new StructType(struct.name).llvmType());
      
      node.add(malloc).add(bitcast);
      
      return bitcast.target;
   }
}
