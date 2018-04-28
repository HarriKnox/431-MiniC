package ast.declaration;


import java.util.List;

import ast.LinedElement;

import ast.type.Type;


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
      return getFieldType(fieldName) != null;
   }
   
   
   public Type getFieldType(String fieldName)
   {
      return this.fields.getDeclarationType(fieldName);
   }
   
   
   public void validate(Structs structs)
   {
      this.fields.validate(structs);
   }
}
