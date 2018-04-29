package ast.type;


import ast.declaration.Structs;


public class StructType extends Type
{
   public final String name;


   public StructType(String name)
   {
      this.name = name;
   }


   public boolean equivalent(Type t)
   {
      return (t instanceof NullType)
            || ((t instanceof StructType)
                  && this.name.equals(((StructType)t).name));
   }
   
   
   public boolean isValid(Structs structs)
   {
      return structs.getDeclaration(this.name) != null;
   }
}
