package ast.lvalue;


import org.antlr.v4.runtime.Token;

import ast.ProgramAST;

import ast.declaration.Function;
import ast.declaration.Variable;
import ast.declaration.Variables;

import common.ErrorPrinter;
import common.Options;

import llvm.LLVMCFGNode;

import llvm.value.variable.LLVMVariable;


public class LvalueId extends Lvalue
{
   public final String id;


   public LvalueId(Token token, String id)
   {
      super(token, 0);

      this.id = id;
   }
   
   
   @Override
   public LLVMVariable buildLLVM(ProgramAST program,
         Function current, Options opts, LLVMCFGNode node)
   {
      LLVMVariable source = findVariable(program.globals, current);
      
      
      if (source == null)
      {
         ErrorPrinter.undeclared(this.token, "variable", this.id);
         return null;
      }
      
      
      return source;
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
