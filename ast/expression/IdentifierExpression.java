package ast.expression;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Variable;
import ast.declaration.Variables;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.targeted.LLVMLoad;

import llvm.value.operand.LLVMOperand;

import llvm.value.operand.register.LLVMParameter;

import llvm.value.variable.LLVMGlobal;
import llvm.value.variable.LLVMLocal;
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
   public LLVMOperand buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      LLVMVariable source = findVariable(program.globals, current);
      
      
      if (source == null)
      {
         ErrorPrinter.undeclared(this.token, "variable", this.id);
         return null;
      }
      
      
      if ((source instanceof LLVMLocal) && !opts.stack)
         return node.readVariable((LLVMLocal)source);
      
      
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
         return variable.llvmLocal();
      
      
      /* Find in globals */
      variable = globals.getVariable(this.id);
      
      if (variable != null)
         return variable.llvmGlobal();
      
      
      /* Not found */
      return null;
   }
}
