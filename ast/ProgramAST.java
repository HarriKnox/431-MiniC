package ast;


import ast.declaration.Function;
import ast.declaration.Functions;
import ast.declaration.Struct;
import ast.declaration.Structs;
import ast.declaration.Variable;
import ast.declaration.Variables;

import common.ErrorPrinter;
import common.Options;

import llvm.ProgramLLVM;

import llvm.declaration.LLVMFunctions;
import llvm.declaration.LLVMGlobals;
import llvm.declaration.LLVMStructs;


public class ProgramAST
{
   public final Structs structs;
   public final Variables globals;
   public final Functions functions;


   public ProgramAST(Structs structs, Variables globals, Functions functions)
   {
      this.structs = structs;
      this.globals = globals;
      this.functions = functions;
   }
   
   
   public ProgramLLVM buildLLVM(Options opts)
   {
      LLVMStructs llvmStructs = this.structs.buildLLVM();
      LLVMGlobals llvmGlobals = this.globals.buildLLVM(this.structs);
      LLVMFunctions llvmFunctions = this.functions.buildLLVM(this);
      
      
      int errors = ErrorPrinter.getErrorCount();
      
      if (errors > 0)
      {
         System.err.println("Found " + errors
               + " errors. Fix them and recompile");
         System.exit(8);
      }
      
      return new ProgramLLVM(llvmStructs, llvmGlobals, llvmFunctions);
   }
   
   
   public Struct getStruct(String name)
   {
      return this.structs.getStruct(name);
   }
   
   
   public Variable getGlobal(String name)
   {
      return this.globals.getVariable(name);
   }
   
   
   public Function getFunction(String name)
   {
      return this.functions.getFunction(name);
   }
}
