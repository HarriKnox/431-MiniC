package ast.expression;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import common.Error;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMInvocation;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;


public class InvocationExpression extends Expression
{
   public final String name;
   public final List<Expression> arguments;


   public InvocationExpression(
         Token token,
         String name,
         List<Expression> arguments)
   {
      super(token, getMax(arguments));

      this.name = name;
      this.arguments = arguments;
   }
   
   
   private static int getMax(List<Expression> args)
   {
      int max = 0;
      
      for (Expression exp : args)
         if (exp.height > max)
            max = exp.height;
      
      return max;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      Function function = program.getFunction(this.name);
      
      
      if (function == null)
      {
         Error.undeclared(this.token, "function", this.name);
         return null;
      }
      
      
      int arglen = this.arguments.size();
      
      
      if (function.parameters.length != arglen)
      {
         Error.wrongArity(this.token, this.name,
               function.parameters.length, arglen);
         
         return null;
      }
      
      
      boolean ok = true;
      
      Iterator<Expression> argerator = this.arguments.iterator();
      Iterator<Type> typerator = function.parameterTypes.iterator();
      
      List<LLVMValue> args = new LinkedList<>();
      
      for (int i = 0; argerator.hasNext(); i++)
      {
         LLVMValue llvmArg = argerator.next().buildLLVM(program, current, node);
         LLVMType paramType = typerator.next().getLLVMType();
         
         
         /* If an error already occurred, no need to dwell on it */
         if (llvmArg == null)
         {
            ok = false;
            continue;
         }
         
         
         if (!llvmArg.type.equals(paramType))
         {
            Error.unexpectedType(this.token, paramType.astString(),
                  "argument " + i, llvmArg.type.astString());
            
            ok = false;
         }
      }
      
      if (!ok)
         return null;
      
      
      LLVMInvocation invocation = new LLVMInvocation(
            this.name,
            function.type.getLLVMType(),
            args);
      
      node.add(invocation);
      
      
      return invocation.target;
   }
}
