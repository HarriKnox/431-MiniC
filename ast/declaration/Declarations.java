package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public abstract class Declarations<T extends Declaration,
      R extends LLVMDeclaration>
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
   
   
   public void removeInvalids(Structs structs)
   {
      Iterator<T> iter = this.declarations.iterator();
      Set<String> names = new HashSet<>();
      
      
      /* Get all unique function names */
      while (iter.hasNext())
      {
         T declaration = iter.next();
         
         
         if (names.contains(declaration.name))
         {
            iter.remove();
            System.err.println("Already got it");
         }
         else
         {
            names.add(declaration.name);
            
            if (!declaration.hasValidType(structs))
            {
               iter.remove();
               System.err.println("Invalid type");
            }
            else
            {
               declaration.removeInvalids(structs);
            }
         }
      }
   }
   
   
   private List<R> buildLLVM(Structs structs,
         Variables globals, Functions functions)
   {
      this.removeInvalids(structs);
      
      
      List<R> llvmDeclarations = new LinkedList<>()
      
      for (T declaration : this.declarations)
         llvmDeclarations.add(declaration.buildLLVM(
               structs,
               globals,
               functions));
      
      return llvmDeclarations;
   }
}
