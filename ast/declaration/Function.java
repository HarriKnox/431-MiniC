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

import llvm.type.LLVMType;

import llvm.value.operand.register.LLVMParameter;
import llvm.value.operand.register.LLVMPhi;

import llvm.value.variable.LLVMLocal;


public class Function extends TokenedElement
{
   public final String name;
   public final Type type;
   public final Variables parameters;
   public final Variables locals;
   public final Statement body;
   public final Variable returnValue;
   
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
      
      this.returnValue = new Variable(null, "return.value", this.type, 0);
      
      
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
      
      
      this.returnValue.llvmLocalSet(this.name, locals.size());
      
      if (!(this.type instanceof VoidType))
         locals.add(this.returnValue.llvmLocal());
      
      
      List<LLVMCFGNode> nodes = getCFGNodes(program, opts);
      
      
      return new LLVMFunction(
            this.name, this.type.llvmType(), params,
            locals, nodes, this.returnValue.llvmLocal());
   }
   
   
   private List<LLVMCFGNode> getCFGNodes(ProgramAST program, Options opts)
   {
      LLVMCFGNode entry = new LLVMCFGNode(false);
      LLVMCFGNode exit = new LLVMCFGNode(false);
      
      
      for (Variable param : this.parameters.variables)
         entry.writeVariable(param.llvmLocal(), param.llvmParameter());
      
      
      buildCFG(program, opts, entry, exit);
      
      
      if (!opts.dirtyCFG)
         exit = LLVMCFGNode.cleanCFG(exit);
      
      
      return recursivisit(exit, opts);
   }
   
   
   private void buildCFG(ProgramAST program, Options opts,
         LLVMCFGNode entry, LLVMCFGNode exit)
   {
      LLVMCFGNode first = new LLVMCFGNode(false);
      
      entry.jump(first);
      
      
      /* Build the CFG and complain if it doesn't return */
      LLVMCFGNode last = this.body.buildLLVM(program, this, opts, first, exit);
      
      
      if (!last.unreachable)
      {
         if (!(this.type instanceof VoidType))
            ErrorPrinter.nonReturn(this.token, this.name);
         
         last.jump(exit);
      }
      
      
      /* Make sure the exit node knows where its return value comes from */
      if (!(this.type instanceof VoidType))
         exit.readVariable(this.returnValue.llvmLocal());
   }
   
   
   private List<LLVMCFGNode> recursivisit(LLVMCFGNode exit, Options opts)
   {
      List<LLVMCFGNode> nodes = new LinkedList<>();
      
      exit.recursivisit(nodes, opts);
      
      resolveAllPhis(nodes);
      
      return nodes;
   }
   
   
   private static void resolveAllPhis(List<LLVMCFGNode> nodes)
   {
      int incompleteCount;
      
      do
      {
         incompleteCount = 0;
         
         for (LLVMCFGNode node : nodes)
         {
            int ic = node.incompletePhis.size();
            
            if (ic > 0)
            {
               incompleteCount += ic;
               
               for (LLVMPhi phi : node.incompletePhis)
               {
                  node.addPhiOperands(phi);
               }
               
               node.incompletePhis.clear();
            }
         }
      
      } while (incompleteCount != 0);
   }
}
