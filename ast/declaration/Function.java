package ast.declaration;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.TokenedElement;
import ast.ProgramAST;

import ast.type.Type;
import ast.type.VoidType;

import ast.statement.Statement;

import common.ErrorPrinter;

import llvm.LLVMCFGNode;

import llvm.declaration.LLVMFunction;

import llvm.instruction.LLVMAlloca;
import llvm.instruction.LLVMStore;
import llvm.instruction.LLVMInstruction;

import llvm.instruction.targeted.LLVMTargetedInstruction;
import llvm.instruction.targeted.LLVMLoad;

import llvm.type.LLVMType;

import llvm.value.variable.LLVMLocal;
import llvm.value.variable.LLVMParameter;
import llvm.value.variable.LLVMRegister;
import llvm.value.variable.LLVMReturnValue;
import llvm.value.variable.LLVMVariable;


public class Function extends TokenedElement
{
   public final String name;
   public final Type type;
   public final Variables parameters;
   public final Variables locals;
   public final Statement body;
   
   public final List<Type> parameterTypes;


   public Function(Token token, String name, Type type,
         Variables params, Variables locals, Statement body)
   {
      super(token);

      this.name = name;
      this.type = type;
      this.parameters = params;
      this.locals = locals;
      this.body = body;
      
      
      /* Get parameter types for function signature */
      this.parameterTypes = new ArrayList<>(params.length);
      
      for (Variable parameter : params.variables)
         this.parameterTypes.add(parameter.type);
   }
   
   
   public boolean hasValidType(Structs structs)
   {
      for (Type type : this.parameterTypes)
         if (!type.isValid(structs))
            return false;
      
      
      return this.type.isValid(structs);
   }
   
   
   public Variable getLocal(String name)
   {
      return this.locals.getVariable(name);
   }
   
   
   public Variable getParameter(String name)
   {
      return this.parameters.getVariable(name);
   }
   
   
   public LLVMFunction buildLLVM(ProgramAST program)
   {
      List<LLVMParameter> params = new ArrayList<>(this.parameters.length);
      
      for (Variable param : this.parameters.variables)
         params.add(new LLVMParameter(
               this.name,
               param.name,
               param.type.llvmType()));
      
      
      List<LLVMCFGNode> nodes = getCFGNodes(program);
      
      
      return new LLVMFunction(this.name,
            this.type.llvmType(), params, nodes);
   }
   
   
   private List<LLVMCFGNode> getCFGNodes(ProgramAST program)
   {
      LLVMCFGNode entry = new LLVMCFGNode();
      LLVMCFGNode exit = new LLVMCFGNode();
      
      /* Build the CFG and complain if it doesn't return */
      LLVMCFGNode last = this.body.buildLLVM(program, this, entry, exit);
      
      if (last != null)
      {
         if (!(this.type instanceof VoidType))
            ErrorPrinter.nonReturn(this.token, this.name);
         
         last.jump(exit);
      }
      
      
      /* Add stack allocations and parameter value stores */
      LLVMCFGNode allocaNode = new LLVMCFGNode();
      
      allocaNode.jump(entry);
      
      for (Variable param : this.parameters.variables)
      {
         LLVMType paramType = param.type.llvmType();
         
         
         LLVMParameter llvmParam = new LLVMParameter(
               this.name, param.name, paramType);
         
         LLVMLocal llvmLocal = new LLVMLocal(
               this.name, param.name, paramType);
         
         
         allocaNode
               .add(new LLVMAlloca(llvmLocal))
               .add(new LLVMStore(llvmLocal, llvmParam));
      }
      
      
      for (Variable local : this.locals.variables)
      {
         LLVMLocal llvmLocal = new LLVMLocal(
               this.name, local.name, local.type.llvmType());
         
         allocaNode.add(new LLVMAlloca(llvmLocal));
      }
      
      
      /* Add return instructions */
      if (this.type instanceof VoidType)
      {
         exit.ret(null);
      }
      else
      {
         LLVMReturnValue returnValue = new LLVMReturnValue(
               this.name,
               this.type.llvmType());
         
         allocaNode.add(new LLVMAlloca(returnValue));
         
         LLVMLoad load = new LLVMLoad(returnValue);
         
         exit.add(load).ret(load.target);
      }
      
      
      /* Sort the nodes */
      List<LLVMCFGNode> nodes = new LinkedList<>();
      
      exit.recursivisit(nodes);
      
      
      /* Set the unique ID number for each node (registers come later) */
      for (LLVMCFGNode node : nodes)
         node.getUID();
      
      
      return nodes;
   }
}
