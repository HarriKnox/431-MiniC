package ast;


import ast.declaration.Functions;
import ast.declaration.Structs;
import ast.declaration.Variables;

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
   
   
   public ProgramLLVM buildLLVM()
   {
      return new ProgramLLVM(
         new LLVMStructs(this.structs.buildLLVM(
               this.structs,
               this.globals,
               this.functions)),
         new LLVMGlobals(this.globals.buildLLVM(
               this.structs,
               this.globals,
               this.functions)),
         new LLVMFunctions(this.functions.buildLLVM(
               this.structs,
               this.globals,
               this.functions)));
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
