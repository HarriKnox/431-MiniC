package ast.declaration;


import ast.type.Type;


public class Struct extends Declaration
{
   public final Variables fields;
   

   public Struct(int lineNum, String name, Variables fields)
   {
      super(lineNum, name);

      this.fields = fields;
   }
   
   
   public void validate(Structs structs)
   {
      return this.fields.validate(structs);
   }
}
