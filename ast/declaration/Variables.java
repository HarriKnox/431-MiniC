package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ast.type.Type;

import common.ErrorPrinter;

import llvm.declaration.LLVMGlobals;

import llvm.value.variable.LLVMGlobal;


import static llvm.value.variable.LLVMGlobal.SCANF_SCRATCH;


public class Variables
{
   public final List<Variable> variables;
   public final int length;
   
   
   public Variables(List<Variable> variables)
   {
      this.variables = variables;
      this.length = variables.size();
   }
   
   
   public Variable getVariable(String name)
   {
      for (Variable variable : this.variables)
         if (variable.name.equals(name))
            return variable;
      
      
      return null;
   }
   
   
   public boolean isValid(String name)
   {
      return this.getVariable(name) != null;
   }
   
   
   public void removeInvalids(Structs structs)
   {
      Iterator<Variable> variter = this.variables.iterator();
      Set<String> names = new HashSet<>();
      
      
      /* Get all unique function names */
      while (variter.hasNext())
      {
         Variable variable = variter.next();
         
         
         if (names.contains(variable.name))
         {
            variter.remove();
            ErrorPrinter.duplicate(variable.token, "variable", variable.name);
         }
         else
         {
            names.add(variable.name);
            
            if (!variable.hasValidType(structs))
            {
               variter.remove();
               ErrorPrinter.unknownStruct(
                     variable.token,
                     variable.type.astString());
            }
         }
      }
   }
   
   
   public void removeInvalids(Structs structs, Variables also)
   {
      Iterator<Variable> paramerator = this.variables.iterator();
      Set<String> names = new HashSet<>();
      
      
      while (paramerator.hasNext())
      {
         Variable param = paramerator.next();
         
         
         if (names.contains(param.name))
         {
            paramerator.remove();
            ErrorPrinter.duplicate(param.token, "variable", param.name);
         }
         else
         {
            names.add(param.name);
            
            if (!param.hasValidType(structs))
            {
               paramerator.remove();
               ErrorPrinter.unknownStruct(param.token, param.type.astString());
            }
         }
      }
      
      
      Iterator<Variable> localerator = also.variables.iterator();
      while (localerator.hasNext())
      {
         Variable local = localerator.next();
         
         
         if (names.contains(local.name))
         {
            localerator.remove();
            ErrorPrinter.duplicate(local.token, "variable", local.name);
         }
         else
         {
            names.add(local.name);
            
            if (!local.hasValidType(structs))
            {
               localerator.remove();
               ErrorPrinter.unknownStruct(local.token, local.type.astString());
            }
         }
      }
   }
   
   
   public LLVMGlobals buildLLVM(Structs structs)
   {
      this.removeInvalids(structs);
      
      
      List<LLVMGlobal> llvmGlobals = new LinkedList<>();
      
      for (Variable variable : this.variables)
         llvmGlobals.add(variable.llvmGlobalSet());
      
      
      llvmGlobals.add(SCANF_SCRATCH);
      
      
      return new LLVMGlobals(llvmGlobals);
   }
}
