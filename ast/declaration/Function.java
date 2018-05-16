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
import common.Options;

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
import llvm.value.variable.LLVMVariable;


public class Function extends TokenedElement
{
   public final String name;
   public final Type type;
   public final Variables parameters;
   public final Variables locals;
   public final Statement body;
   
   public final List<Type> parameterTypes;
   
   
   private Variable returnValue;


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
      Variable local = this.locals.getVariable(name);
      
      if (local == null)
         local = this.getParameter(name);
      
      return local;
   }
   
   
   public Variable getParameter(String name)
   {
      return this.parameters.getVariable(name);
   }
   
   
   public Variable returnValue()
   {
      return this.returnValue;
   }
   
   
   public LLVMFunction buildLLVM(ProgramAST program, Options opts)
   {
      int paramLen = this.parameters.length;
      
      List<LLVMParameter> params = new ArrayList<>(paramLen);
      List<LLVMLocal> locals = new ArrayList<>(
            paramLen + this.locals.length + 1);
      
      
      for (Variable param : this.parameters.variables)
      {
         params.add(param.llvmParameterSet(this.name, param.index));
         locals.add(param.llvmLocalSet(this.name, param.index));
      }
      
      
      for (Variable local : this.locals.variables)
         locals.add(local.llvmLocalSet(this.name, local.index + paramLen));
      
      
      if (!(this.type instanceof VoidType))
      {
         this.returnValue = new Variable(null, "return.value", this.type, 0);
         locals.add(this.returnValue.llvmLocalSet(
               this.name, locals.size() + 1));
      }
      
      
      List<LLVMCFGNode> nodes = getCFGNodes(program, opts);
      
      
      return new LLVMFunction(
            this.name, this.type.llvmType(), params,
            locals, nodes, this.returnValue.llvmLocal());
   }
   
   
   private List<LLVMCFGNode> getCFGNodes(ProgramAST program, Options opts)
   {
      LLVMCFGNode entry = buildEntryNode();
      LLVMCFGNode exit = buildExitNode();
      
      buildCFG(program, entry, exit);
      
      
      if (!opts.dirtycfg)
         exit = LLVMCFGNode.cleanCFG(exit);
      
      
      return recursivisit(exit);
   }
   
   
   private LLVMCFGNode buildEntryNode()
   {
      LLVMCFGNode entry = new LLVMCFGNode(false);
      
      
      /* Allocate space for return value */
      if (this.returnValue != null)
         entry.add(new LLVMAlloca(this.returnValue.llvmLocal()));
      
      
      /* Allocate space and store value for each parameter */
      for (Variable param : this.parameters.variables)
      {
         LLVMType paramType = param.type.llvmType();
         
         LLVMParameter llvmParam = param.llvmParameter();
         LLVMLocal llvmLocal = param.llvmLocal();
         
         entry .add(new LLVMAlloca(llvmLocal))
               .add(new LLVMStore(llvmLocal, llvmParam));
      }
      
      
      /* Allocate space for each local */
      for (Variable local : this.locals.variables)
      {
         LLVMLocal llvmLocal = local.llvmLocal();
         
         entry.add(new LLVMAlloca(llvmLocal));
      }
      
      
      return entry;
   }
   
   
   private LLVMCFGNode buildExitNode()
   {
      LLVMCFGNode exit = new LLVMCFGNode(false);
      
      
      /* Add return instructions */
      if (this.returnValue == null)
      {
         exit.ret(null);
      }
      else
      {
         LLVMLoad load = new LLVMLoad(this.returnValue.llvmLocal());
         
         exit.add(load).ret(load.target);
      }
      
      
      return exit;
   }
   
   
   private void buildCFG(ProgramAST program,
         LLVMCFGNode entry, LLVMCFGNode exit)
   {
      LLVMCFGNode first = new LLVMCFGNode(false);
      
      entry.jump(first);
      
      
      /* Build the CFG and complain if it doesn't return */
      LLVMCFGNode last = this.body.buildLLVM(program, this, first, exit);
      
      
      if (!last.unreachable)
      {
         if (!(this.type instanceof VoidType))
            ErrorPrinter.nonReturn(this.token, this.name);
         
         last.jump(exit);
      }
   }
   
   
   private List<LLVMCFGNode> recursivisit(LLVMCFGNode exit)
   {
      List<LLVMCFGNode> nodes = new LinkedList<>();
      
      exit.recursivisit(nodes);
      
      
      return nodes;
   }
}
