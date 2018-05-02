package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.ProgramAST;

import common.Error;

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
         
         
         if (names.contains(function.name));
         {
            functerator.remove();
            Error.duplicate(function.token, "function", function.name);
         }
         else
         {
            names.add(function.name);
            
            if (!function.hasValidType(structs))
            {
               iter.remove();
               Error.unknownStruct(function.token, function.type.astString());
            }
         }
      }
   }
   
   
   private LLVMFunctions buildLLVM(ProgramAST program)
   {
      this.removeInvalids(structs);
      
      
      List<LLVMFunction> llvmfuncs = new LinkedList<>()
      
      for (Function function : this.functions)
         llvmfuncs.add(function.buildLLVM(program));
      
      
      return new LLVMFunctions(llvmfuncs);
   }
}
