package ast;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TypeDeclaration
   extends LinedElement
{
   private final String name;
   private final Map<String, Type> fields;

   public TypeDeclaration(int lineNum, String name, List<Declaration> fields)
   {
      super(lineNum);
      this.name = name;
      this.fields = new LinkedHashMap<>();
      
      for (Declaration field : fields)
      {
         if (this.fields.containsKey(field.name))
            throw InvalidStateException();
         
         this.fields.put(field.name, field.type);
      }
   }
   
   
   public String getName()
   {
      return this.name;
   }
   
   
}
