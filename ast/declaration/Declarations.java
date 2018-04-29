package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public abstract class Declarations<T extends Declaration>
{
   final List<T> declarations;
   
   
   Declarations(List<T> declarations)
   {
      this.declarations = declarations;
   }
   
   
   public T getDeclaration(String name)
   {
      for (T declaration : this.declarations)
         if (declaration.name.equals(name))
            return declaration;
      
      
      return null;
   }
   
   
   public boolean isValid(String name)
   {
      return this.getDeclaration(name) != null;
   }
   
   
   public void removeDuplicates()
   {
      Iterator<T> iter = this.declarations.iterator();
      Set<String> names = new HashSet<>();
      
      
      /* Get all unique function names */
      while (iter.hasNext())
      {
         String name = iter.next().name;
         
         
         if (names.contains(name))
         {
            iter.remove();
            System.err.println("Already got it");
         }
         else
         {
            names.add(name);
         }
      }
   }
   
   
   public void removeInvalids(Structs structs, Variables globals)
   {
      Iterator<T> iter = this.declarations.iterator();
      
      while (iter.hasNext())
      {
         T declaration = iter.next();
         
         if (!t.isValid(structs, globals))
         {
            iter.remove();
         }
      }
   }
}
