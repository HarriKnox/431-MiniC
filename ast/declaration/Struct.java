package ast.declaration;


import java.util.List;

import ast.LinedElement;


public class Struct extends LinedElement
{
   public final String name;
   public final Declarations fields;
   

   public Struct(int lineNum, String name, Declarations fields)
   {
      super(lineNum);

      this.name = name;
      this.fields = fields;
   }
   
   
   public boolean isField(String fieldName)
   {
      return findField(fieldName) != null;
   }
   
   
   public Type getFieldType(String fieldName)
   {
      Declaration field = findField(fieldName);
      
      return (field == null) ? null : field.type;
   }
   
   
   private Declaration findField(String fieldName)
   {
      for (Declaration field : this.fields)
         if (field.name.equals(fieldName))
            return field;
      
      
      return null;
   }
   
   
   public void validate(Structs structs)
   {
      this.fields.validate(structs);
   }
}
