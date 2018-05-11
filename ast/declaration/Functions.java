package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ast.ProgramAST;

import ast.declaration.Structs;

import ast.type.IntType;

import common.ErrorPrinter;
import common.Options;

import llvm.declaration.LLVMFunction;
import llvm.declaration.LLVMFunctions;


public class Functions
{
   public final List<Function> functions;
   
   
   public Functions(List<Function> functions)
   {
      this.functions = functions;
   }
   
   
   public Function getFunction(String name)
   {
      for (Function function : this.functions)
         if (function.name.equals(name))
            return function;
      
      return null;
   }
   
   
   public boolean isValid(String name)
   {
      return this.getFunction(name) != null;
   }
   
   
   public void removeInvalids(Structs structs)
   {
      Iterator<Function> functerator = this.functions.iterator();
      Set<String> names = new HashSet<>();
      
      
      while (functerator.hasNext())
      {
         Function function = functerator.next();
         
         
         if (names.contains(function.name))
         {
            functerator.remove();
            ErrorPrinter.duplicate(function.token, "function", function.name);
         }
         else
         {
            names.add(function.name);
            
            if (!function.hasValidType(structs))
            {
               functerator.remove();
               ErrorPrinter.unknownStruct(
                     function.token,
                     function.type.astString());
            }
         }
      }
   }
   
   
   public LLVMFunctions buildLLVM(ProgramAST program, Options opts)
   {
      this.removeInvalids(program.structs);
      
      checkMain();
      
      
      List<LLVMFunction> llvmfuncs = new LinkedList<>();
      
      for (Function function : this.functions)
         llvmfuncs.add(function.buildLLVM(program, opts));
      
      
      return new LLVMFunctions(llvmfuncs);
   }
   
   
   public void checkMain()
   {
      Function main = this.getFunction("main");
      
      if (main == null)
      {
         ErrorPrinter.printOut("No function `main() int`");
         return;
      }
      
      if (!main.type.equivalent(new IntType()))
         ErrorPrinter.badMainType(main.token, main.type.astString());
      
      if (main.parameters.length != 0)
         ErrorPrinter.badMainArity(main.token);
   }
}
