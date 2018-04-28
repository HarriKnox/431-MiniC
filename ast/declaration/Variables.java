package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.type.Type;


public class Variables
{
   public final List<Variable> variables;


   public Variables(List<Variable> variables)
   {
      this.variables = variables;
   }
   
   
   public Type getVariableType(String variableName)
   {
      for (Variable variable : this.variables)
         if (variable.name.equals(variableName))
            return variable.type;
      
      
      return null;
   }
   
   
   public void validate(Structs structs)
   {
      Iterator<Variable> declarator = this.variables.iterator();
      Set<String> variableNames = new HashSet<>();
      
      
      /* Get all unique variable names (in this namespace) */
      while (declarator.hasNext())
      {
         String variableName = declarator.next().name;
         
         
         if (variableNames.contains(variableName))
         {
            declarator.remove();
            System.err.println("Already got it");
         }
         else
         {
            variableNames.add(variableName);
         }
      }
      
      
      /* Validate all uniquely named variables */
      for (Variable variable : this.variables)
         variable.validate(structs);
   }
   
   
   public void validate(Structs structs, Variables also)
   {
      Iterator<Variable> declarator = this.variables.iterator();
      Iterator<Variable> alsorator = also.variables.iterator();
      Set<String> variableNames = new HashSet<>();
      
      
      /* Get all unique variable names (in this namespace) */
      while (declarator.hasNext())
      {
         String variableName = declarator.next().name;
         
         
         if (variableNames.contains(variableName))
         {
            declarator.remove();
            System.err.println("Already got it");
         }
         else
         {
            variableNames.add(variableName);
         }
      }
      
      /* Get all unique variable names (in this namespace) */
      while (alsorator.hasNext())
      {
         String variableName = alsorator.next().name;
         
         
         if (variableNames.contains(variableName))
         {
            alsorator.remove();
            System.err.println("Already got it");
         }
         else
         {
            variableNames.add(variableName);
         }
      }
      
      
      /* Validate all uniquely named variables */
      for (Variable variable : this.variables)
         variable.validate(structs);
      
      
      /* Validate all uniquely named variables */
      for (Variable variable : also.variables)
         variable.validate(structs);
   }
}
