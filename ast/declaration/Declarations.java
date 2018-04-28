package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.type.Type;


public class Declarations
{
   public final List<Declaration> declarations;


   public Declarations(List<Declaration> declarations)
   {
      this.declarations = declarations;
   }
   
   
   public Type getDeclarationType(String declarationName)
   {
      for (Declaration declaration : this.declarations)
         if (declaration.name.equals(declarationName))
            return declaration.type;
      
      
      return null;
   }
   
   
   public void validate(Structs structs)
   {
      Iterator<Declaration> declarator = this.declarations.iterator();
      Set<String> declarationNames = new HashSet<>();
      
      
      /* Get all unique declaration names (in this namespace) */
      while (declarator.hasNext())
      {
         String declarationName = declarator.next().name;
         
         
         if (declarationNames.contains(declarationName))
         {
            declarator.remove();
            System.err.println("Already got it");
         }
         else
         {
            declarationNames.add(declarationName);
         }
      }
      
      
      /* Validate all uniquely named declarations */
      for (Declaration declaration : this.declarations)
         declaration.validate(structs);
   }
   
   
   public void validate(Structs structs, Declarations also)
   {
      Iterator<Declaration> declarator = this.declarations.iterator();
      Iterator<Declaration> alsorator = also.declarations.iterator();
      Set<String> declarationNames = new HashSet<>();
      
      
      /* Get all unique declaration names (in this namespace) */
      while (declarator.hasNext())
      {
         String declarationName = declarator.next().name;
         
         
         if (declarationNames.contains(declarationName))
         {
            declarator.remove();
            System.err.println("Already got it");
         }
         else
         {
            declarationNames.add(declarationName);
         }
      }
      
      /* Get all unique declaration names (in this namespace) */
      while (alsorator.hasNext())
      {
         String declarationName = alsorator.next().name;
         
         
         if (declarationNames.contains(declarationName))
         {
            alsorator.remove();
            System.err.println("Already got it");
         }
         else
         {
            declarationNames.add(declarationName);
         }
      }
      
      
      /* Validate all uniquely named declarations */
      for (Declaration declaration : this.declarations)
         declaration.validate(structs);
      
      
      /* Validate all uniquely named declarations */
      for (Declaration declaration : also.declarations)
         declaration.validate(structs);
   }
}
