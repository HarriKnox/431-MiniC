package ast;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TypeDeclaration
   extends LinedElement
{
   private static final Map<String, TypeDeclaration> validTypeDeclarations = new HashMap<>();
   
   private final String name;
   private final Map<String, Type> fields;
   private final List<String> errors;

   public TypeDeclaration(int lineNum, String name, List<Declaration> fields)
   {
      super(lineNum);
      this.name = name;
      this.fields = new LinkedHashMap<>();
      
      for (Declaration field : fields)
      {
         if (this.fields.containsKey(field.name))
            
         
         this.fields.put(field.name, field.type);
      }
   }
   
   
   public String getName()
   {
      return this.name;
   }
   
   
}
