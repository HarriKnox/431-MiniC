package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Functions
{
   public final List<Function> functions;


   public Functions(List<Function> functions)
   {
      this.functions = functions;
   }
   
   
   public void validate(Structs structs, Declarations globals)
   {
      Iterator<Function> functerator = this.functions.iterator();
      Set<String> functionNames = new HashSet<>();
      
      
      /* Get all unique function names */
      while (functerator.hasNext())
      {
         String functionName = functerator.next().name;
         
         
         if (functionNames.contains(functionName))
         {
            functerator.remove();
            System.err.println("Already got it");
         }
         else
         {
            functionNames.add(functionName);
         }
      }
      
      
      /* Validate all uniquely named functions */
      for (Function function : this.functions)
         function.validate(structs, globals);
   }
}
