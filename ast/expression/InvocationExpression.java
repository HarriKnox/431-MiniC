package ast.expression;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;

import ast.type.Type;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMCall;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.constant.LLVMNull;


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
   public LLVMOperand buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      Function function = this.getFunction(program);
      
      if (function == null)
         return null;
      
      
      List<LLVMOperand> args = this.gatherArguments(
            function, program, current, node);
      
      if (args == null)
         return null;
      
      
      LLVMCall call = new LLVMCall(
            function.name,
            function.type.llvmType(),
            args);
      
      node.add(call);
      
      
      return call.target;
   }
   
   
   public Function getFunction(ProgramAST program)
   {
      Function function = program.getFunction(this.name);
      
      if (function == null)
         ErrorPrinter.undeclared(this.token, "function", this.name);
      
      return function;
   }
   
   
   public List<LLVMOperand> gatherArguments(Function function,
         ProgramAST program, Function current, LLVMCFGNode node)
   {
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
      
      List<LLVMOperand> args = new LinkedList<>();
      
      for (int i = 0; argerator.hasNext(); i++)
      {
         LLVMOperand llvmArg = argerator
               .next()
               .buildLLVM(program, current, node);
         
         LLVMType paramType = typerator.next().llvmType();
         
         
         /* If an error already occurred, no need to dwell on it */
         if (llvmArg == null)
         {
            ok = false;
         }
         else if (!llvmArg.type.equivalent(paramType))
         {
            ErrorPrinter.unexpectedType(this.token, paramType.astString(),
                  "argument " + i, llvmArg.type.astString());
            
            ok = false;
         }
         else
         {
            if (llvmArg instanceof LLVMNull)
               llvmArg = new LLVMNull(paramType);
            
            args.add(llvmArg);
         }
      }
      
      
      return ok ? args : null;
   }
}
