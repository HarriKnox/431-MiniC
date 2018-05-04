package ast.statement;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.expression.Expression;

import ast.type.Type;
import ast.type.VoidType;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMCallVoid;
import llvm.instruction.LLVMInstruction;

import llvm.instruction.targeted.LLVMCall;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public class InvocationStatement extends Statement
{
   public final String name;
   public final List<Expression> arguments;


   public InvocationStatement(Token token, String name, List<Expression> args)
   {
      super(token);

      this.name = name;
      this.arguments = args;
   }
   
   
   @Override
   public LLVMCFGNode buildLLVM(ProgramAST program,
         Function current, LLVMCFGNode node, LLVMCFGNode exit)
   {
      Function function = program.getFunction(this.name);
      
      
      if (function == null)
      {
         ErrorPrinter.undeclared(this.token, "function", this.name);
         return null;
      }
      
      
      int arglen = this.arguments.size();
      
      
      if (function.parameters.length != arglen)
      {
         ErrorPrinter.wrongArity(this.token, this.name,
               function.parameters.length, arglen);
         
         return null;
      }
      
      
      boolean ok = true;
      
      Iterator<Expression> argerator = this.arguments.iterator();
      Iterator<Type> typerator = function.parameterTypes.iterator();
      
      List<LLVMValue> args = new LinkedList<>();
      
      
      for (int i = 0; argerator.hasNext(); i++)
      {
         LLVMValue llvmArg = argerator
               .next()
               .buildLLVM(program, current, node);
         
         LLVMType paramType = typerator.next().getLLVMType();
         
         
         /* If an error already occurred, no need to dwell on it */
         if (llvmArg == null)
         {
            ok = false;
            continue;
         }
         
         
         if (!llvmArg.type.equals(paramType))
         {
            ErrorPrinter.unexpectedType(this.token, paramType.astString(),
                  "argument " + i, llvmArg.type.astString());
            
            ok = false;
         }
      }
      
      
      if (!ok)
         return null;
      
      
      LLVMInstruction call;
      
      if (function.type instanceof VoidType)
         call = new LLVMCallVoid(this.name, args);
      
      else
         call = new LLVMCall(this.name, function.type.getLLVMType(), args);
      
      
      return node.add(call);
   }
}
