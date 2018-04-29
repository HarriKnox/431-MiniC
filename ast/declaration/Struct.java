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
   
   
   @Override
   public boolean hasValidType(Structs structs)
   {
      return true;
   }
   
   
   @Override
   public void removeInvalids(Structs structs)
   {
      this.fields.removeInvalids(structs);
   }
}
