package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.type.Type;

import llvm.declaration.LLVMGlobal;
import llvm.declaration.LLVMGlobals;


public class Variables
{
   public final List<Variable> variables;
   
   
   public Variables(List<Variable> variables)
   {
      this.variables = variables;
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
      return this.getVariables(name) != null;
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
            System.err.println("Already got it");
         }
         else
         {
            names.add(variable.name);
            
            if (!variable.hasValidType(structs))
            {
               variter.remove();
               System.err.println("Invalid type");
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
         Variable parameter = paramerator.next();
         
         
         if (names.contains(parameter.name))
         {
            paramerator.remove();
            System.err.println("Already got it");
         }
         else
         {
            names.add(parameter.name);
            
            if (!parameter.hasValidType(structs))
            {
               paramerator.remove();
               System.err.println("Invalid type");
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
            System.err.println("Already got it");
         }
         else
         {
            names.add(local.name);
            
            if (!localerator.hasValidType(structs))
            {
               localerator.remove();
               System.err.println("InvalidType");
            }
         }
      }
   }
   
   
   private LLVMGlobals buildLLVM(Structs structs)
   {
      this.removeInvalids(structs);
      
      
      List<LLVMGlobal> llvmGlobals = new LinkedList<>()
      
      for (Variable variable : this.variabls)
         llvmGlobals.add(variable.buildLLVM(structs, globals, functions));
      
      return new LLVMGlobals(llvmGlobals);
   }
}
