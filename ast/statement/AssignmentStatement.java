package ast.statement;


import ast.declaration.Function;
import ast.declaration.Functions;
import ast.declaration.Structs;
import ast.declaration.Variables;

import ast.expression.Expression;

import ast.lvalue.Lvalue;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;


public class AssignmentStatement extends Statement
{
   public final Lvalue target;
   public final Expression source;


   public AssignmentStatement(int lineNum, Lvalue target, Expression source)
   {
      super(lineNum);

      this.target = target;
      this.source = source;
   }
   
   
   public LLVMCFGNode buildLLVM(Structs structs, Variables globals,
         Functions functions, Function currentFunction,
         LLVMCFGNode node, LLVMCFGNode exit)
   {
      
   }
}
