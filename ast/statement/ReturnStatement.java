package ast.statement;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.constant.LLVMNull;


public class ReturnStatement extends Statement
{
   public final Expression expression;


   public ReturnStatement(Token token, Expression expression)
   {
      super(token);

      this.expression = expression;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program, Function current,
         Options opts, LLVMCFGNode node, LLVMCFGNode exit)
   {
      LLVMOperand value = this.expression.buildLLVM(
            program, current, opts, node);
      
      LLVMType retType = current.type.llvmType();
      
      
      if (value != null)
      {
         if (!value.type.equivalent(retType))
            ErrorPrinter.badReturn(
                  this.expression.token,
                  retType.astString(),
                  value.type.astString());
         
         
         if (!(value.type instanceof LLVMVoidType))
         {
            if (value instanceof LLVMNull)
               value = new LLVMNull(current.type.llvmType());
            
            
            if (!opts.stack)
            {
               node.writeVariable(current.returnValue.llvmLocal(), value);
            }
            
            else
            {
               LLVMStore store = new LLVMStore(
                     current.returnValue.llvmLocal(),
                     value);
               
               node.add(store);
            }
         }
      }
      
      
      node.jump(exit);
      
      
      return new LLVMCFGNode(true);
   }
}
