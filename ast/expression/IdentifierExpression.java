package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Variable;
import ast.declaration.Variables;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMLoad;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMVariable;


public class IdentifierExpression extends Expression
{
   public final String id;


   public IdentifierExpression(Token token, String id)
   {
      super(token, 0);

      this.id = id;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      LLVMVariable source = findVariable(program.globals, current);
      
      
      if (source == null)
      {
         System.err.println("line " + exp.line + " variable " + exp.id + " not declared");
         return null;
      }
      
      
      LLVMLoad load = new LLVMLoad(source);
      
      node.add(load);
      
      return load.target;
   }
   
   
   private LLVMVariable findVariable(Variables globals, Function current)
   {
      Variable variable;
      
      
      /* Find in locals */
      variable = current.getLocal(this.id);
      
      if (variable != null)
         return new LLVMLocal(
               current.name,
               this.id,
               variable.type.getLLVMType());
      
      
      /* Find in parameters */
      variable = current.getParameter(this.id);
      
      if (variable != null)
         return new LLVMParameter(
               current.name,
               this.id,
               variable.type.getLLVMType());
      
      
      /* Find in globals */
      variable = globals.getVariable(this.id);
      
      if (variable != null)
         return new LLVMGlobal(this.id, variable.type.getLLVMType());
      
      
      /* Not found */
      return null;
   }
}
